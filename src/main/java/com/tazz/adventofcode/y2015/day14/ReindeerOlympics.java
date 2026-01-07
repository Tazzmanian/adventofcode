package com.tazz.adventofcode.y2015.day14;

import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import reactor.core.publisher.Mono;

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
}
