package com.tazz.adventofcode.y2015.day2;

import com.tazz.adventofcode.common.Parser;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class BoxParser implements Parser<Box> {
    @Override
    public Flux<Box> parse(Flux<String> source) {
        return source
//                .doOnError(e -> log.error("Upsteam error", e))
                .map(this::parse);
//                .doOnError(e -> log.error("Downsteam error", e));
    }

    private Box parse(String line) {
        var sides = line.split("x");
        return new Box(Integer.parseInt(sides[0]),
                Integer.parseInt(sides[1]),
                Integer.parseInt(sides[2]));
    }

}
