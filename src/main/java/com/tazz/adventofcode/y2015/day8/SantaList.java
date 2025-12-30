package com.tazz.adventofcode.y2015.day8;

import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import reactor.core.publisher.Mono;

public class SantaList {
    private final ParserContext<Gift> parserContext;


    public SantaList() {
        parserContext = new ParserContext<>(new GiftParser(), new LineFileReaderImpl());
    }

    public Mono<Integer> getGiftsSpace(String classPath) {
        return parserContext.parseClasspathFile(classPath)
                .map(Gift::size)
                .reduce(0, Integer::sum);
    }

    public Mono<Integer> getGiftsEncodedSpace(String classPath) {
        return parserContext.parseClasspathFile(classPath)
                .map(Gift::encodedSize)
                .reduce(0, Integer::sum);
    }
}
