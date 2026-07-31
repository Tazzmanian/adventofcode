package com.tazz.adventofcode.y2015.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import com.tazz.adventofcode.y2015.day16.mapper.AuntComparator;

@SpringBootTest
@EmbeddedKafka(topics = "aunt-sue", bootstrapServersProperty = "spring.kafka.bootstrap-servers")
public class AuntTest {

    @Autowired
    private AuntComparator auntComparator;

    @Test
    public void contextLoads() {
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day16/input.txt, Sue 40",
    })
    public void shouldFindExpectedAuntTask1(String file, String expected) throws IOException {
        var resource = getClass().getResourceAsStream(file);
        Objects.requireNonNull(resource, "Resource not found: " + file);

        try (var reader = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))) {
            List<String> matchingAunts = reader.lines()
                    .map(this::parseAuntLine)
                    .map(auntComparator::compareAunt)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            org.junit.jupiter.api.Assertions.assertTrue(
                    matchingAunts.contains(expected),
                    () -> "Expected aunt '" + expected + "' among matched results " + matchingAunts);
        }
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day16/input.txt, Sue 241",
    })
    public void shouldFindExpectedAuntTask2(String file, String expected) throws IOException {
        var resource = getClass().getResourceAsStream(file);
        Objects.requireNonNull(resource, "Resource not found: " + file);

        try (var reader = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))) {
            List<String> matchingAunts = reader.lines()
                    .map(this::parseAuntLine)
                    .map(auntComparator::compareAunt_v2)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            System.out.println("Matching aunts: " + matchingAunts);

            org.junit.jupiter.api.Assertions.assertTrue(
                    matchingAunts.contains(expected),
                    () -> "Expected aunt '" + expected + "' among matched results " + matchingAunts);
        }
    }

    private Aunt parseAuntLine(String line) {
        String[] parts = line.split(": ", 2);
        String name = parts[0];
        String[] attributesParts = parts[1].split(", ");
        HashMap<String, Integer> attributes = new HashMap<>();

        for (String attributePart : attributesParts) {
            String[] attributeKeyValue = attributePart.split(": ");
            attributes.put(attributeKeyValue[0], Integer.parseInt(attributeKeyValue[1]));
        }

        Aunt aunt = new Aunt();
        aunt.setName(name);
        aunt.setAttributes(attributes);
        return aunt;
    }
}
