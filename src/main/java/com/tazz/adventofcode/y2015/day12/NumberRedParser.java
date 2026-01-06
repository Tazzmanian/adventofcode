package com.tazz.adventofcode.y2015.day12;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberRedParser implements Parser<Integer> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Flux<Integer> parse(Flux<String> source) {
        return source.map(JsonRedSum::sumIgnoringRedObjects);
    }
}
