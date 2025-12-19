package com.tazz.adventofcode.y2015.day1;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

public class FloorParser implements Parser<Character> {
    @Override
    public Flux<Character> parse(Flux<String> source) {
        return source.single().flatMapMany(s -> Flux.fromStream(s.chars().mapToObj(c -> (char) c)));
    }

}
