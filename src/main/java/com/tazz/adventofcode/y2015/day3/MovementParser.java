package com.tazz.adventofcode.y2015.day3;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

public class MovementParser implements Parser<Movement> {
    @Override
    public Flux<Movement> parse(Flux<String> source) {
        return source.single().flatMapMany(s -> Flux.fromStream(s.chars().mapToObj(c -> Movement.fromChar((char)c))));
    }
}
