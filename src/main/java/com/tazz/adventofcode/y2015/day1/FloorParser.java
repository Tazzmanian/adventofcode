package com.tazz.adventofcode.y2015.day1;

import com.tazz.adventofcode.common.FileReaderImpl;
import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

import java.nio.file.Path;

public class FloorParser implements Parser<Character> {
    @Override
    public Flux<Character> parse(String input) {
        return Flux.fromStream(
                input.chars()
                        .mapToObj(c -> (char) c)
        );
    }

}
