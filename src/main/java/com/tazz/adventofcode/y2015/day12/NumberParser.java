package com.tazz.adventofcode.y2015.day12;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberParser implements Parser<Integer> {
    static final Pattern INT = Pattern.compile("-?\\d+");

    @Override
    public Flux<Integer> parse(Flux<String> source) {
        return source.flatMap(s -> {
            Matcher m = INT.matcher(s);
            return Flux.generate(
                    sink -> {
                        if (m.find()) {
                            sink.next(Integer.parseInt(m.group()));
                        } else {
                            sink.complete();
                        }
                    }).cast(Integer.class);
        });
    }
}
