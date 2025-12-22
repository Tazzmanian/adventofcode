package com.tazz.adventofcode.y2015.day4;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

public class SecretKeyParser implements Parser<String> {
    @Override
    public Flux<String> parse(Flux<String> source) {
        return source;
    }
}
