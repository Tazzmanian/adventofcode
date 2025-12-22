package com.tazz.adventofcode.y2015.day5;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

public class ListParser implements Parser<String> {
    @Override
    public Flux<String> parse(Flux<String> source) {
        return source;
    }
}
