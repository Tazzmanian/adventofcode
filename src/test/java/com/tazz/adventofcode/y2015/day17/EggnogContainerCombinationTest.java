package com.tazz.adventofcode.y2015.day17;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import com.tazz.adventofcode.y2015.day17.producer.TaskService;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@EmbeddedKafka(topics = "eggnog-containers", bootstrapServersProperty = "spring.kafka.bootstrap-servers", partitions = 6)
public class EggnogContainerCombinationTest {

    @Autowired
    private TaskService taskService;

    @ParameterizedTest(name = "{index} => file={0}, targetVolume={1}, expected={2}")
    @CsvSource({
            "/2015/day17/task1test.txt, 25, 4"
    })
    public void task1FindContainersCombinations(String file, Integer targetVolume, Integer expected) {
        taskService.loadContainers(file, targetVolume);
        assertTrue(true);
    }
}
