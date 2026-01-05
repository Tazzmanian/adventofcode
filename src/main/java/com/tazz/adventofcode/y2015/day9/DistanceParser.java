package com.tazz.adventofcode.y2015.day9;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

public class DistanceParser implements Parser<Distance> {
    @Override
    public Flux<Distance> parse(Flux<String> source) {
        return source.map(x -> {
            String[] arr = x.split(" ");
            return new Distance(new City(arr[0]), new City(arr[2]), Integer.parseInt(arr[4]));
        });
    }
}
