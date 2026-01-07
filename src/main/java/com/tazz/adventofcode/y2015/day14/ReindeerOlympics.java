package com.tazz.adventofcode.y2015.day14;

import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ReindeerOlympics {

    private ParserContext<ReindeerStats> parserContext;

    public ReindeerOlympics() {
        parserContext = new ParserContext<>(new ReindeerStatsParser(), new LineFileReaderImpl());
    }

    public Mono<Integer> getMaxDistanceAfter(String filepath, int seconds) {
        return parserContext.parseClasspathFile(filepath)
                .map(x -> x.distanceAfter(seconds))
                .reduce(0, Integer::max);
    }

    public Mono<Integer> getMaxPoints(String filepath, int seconds) {
        Mono<List<ReindeerStats>> statsMono = parserContext.parseClasspathFile(filepath).collectList();

        return statsMono
                .flatMap(stats ->
                        Flux.range(1, seconds)
                                .reduce(new HashMap<String, Integer>(), (points, second) -> {
                                    StarCalculator.awardPointsAtSecond(stats, second, points);
                                    return points;
                                })
                )
                .map(points -> Collections.max(points.values()));
    }
}
