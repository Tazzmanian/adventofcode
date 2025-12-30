package com.tazz.adventofcode.y2015.day8;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

public class GiftParser implements Parser<Gift> {
    @Override
    public Flux<Gift> parse(Flux<String> source) {
        return source.map(Gift::new);
    }
}
