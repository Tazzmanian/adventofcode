package com.tazz.adventofcode.y2015.day14;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

public class ReindeerStatsParser implements Parser<ReindeerStats> {
    @Override
    public Flux<ReindeerStats> parse(Flux<String> source) {
        return source.map(x -> {
            String[] arr = x.split(" ");
            return new ReindeerStats(arr[0], Integer.parseInt(arr[3]),
                    Integer.parseInt(arr[6]), Integer.parseInt(arr[13]));
        });
    }
}
