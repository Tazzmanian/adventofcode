package com.tazz.adventofcode.y2015.day16.reader;

import java.util.HashMap;

import com.tazz.adventofcode.common.Parser;
import com.tazz.adventofcode.y2015.day16.Aunt;

import reactor.core.publisher.Flux;

public class AuntParser implements Parser<Aunt> {

    @Override
    public Flux<Aunt> parse(Flux<String> source) {
        return source.map(this::parseLine);
    }

    private Aunt parseLine(String line) {
        String[] parts = line.split(": ", 2);
        String name = parts[0];
        String[] attributesParts = parts[1].split(", ");
        HashMap<String, Integer> attributes = new HashMap<>();
        for (String attributePart : attributesParts) {
            String[] attributeKeyValue = attributePart.split(": ");
            String key = attributeKeyValue[0];
            Integer value = Integer.parseInt(attributeKeyValue[1]);
            attributes.put(key, value);
        }
        Aunt aunt = new Aunt();
        aunt.setName(name);
        aunt.setAttributes(attributes);
        return aunt;
    }
}
