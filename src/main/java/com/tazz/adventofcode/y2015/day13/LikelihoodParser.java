package com.tazz.adventofcode.y2015.day13;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

public class LikelihoodParser implements Parser<Neighbor> {
    @Override
    public Flux<Neighbor> parse(Flux<String> source) {
        return source.map(this::parse);
    }

    private Neighbor parse(String text) {
        String[] arr = text.split(" ");
        String person1 = arr[0];
        String person2 = arr[arr.length - 1].substring(0, arr[arr.length - 1].length() - 1);
        int likes = Integer.parseInt(arr[3]);

        return new Neighbor(person1, person2, text.contains("gain") ? likes : -likes);
    }
}
