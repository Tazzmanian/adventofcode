package com.tazz.adventofcode.y2015.day2;

import com.tazz.adventofcode.common.Parser;
import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.FileReadStrategy;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import com.tazz.adventofcode.common.readers.WholeFileReadStrategy;
import com.tazz.adventofcode.y2015.day1.FloorParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class BoxWrapper {

    private final ParserContext<Box> parserContext;

    public BoxWrapper() {
        FileReadStrategy fileReader = new LineFileReaderImpl();
        Parser<Box> parser = new BoxParser();
        this.parserContext = new ParserContext<>(parser, fileReader);
    }

    public Mono<Integer> totalWrapper(String classpathFile) {
        return parserContext.parseClasspathFile(classpathFile)
                .map(Box::area)
//                .log()
                .reduce(0, Integer::sum);
    }

    public Mono<Integer> totalRibbon(String classpathFile) {
        return parserContext.parseClasspathFile(classpathFile)
                .map(Box::ribbon)
//                .log()
                .reduce(0, Integer::sum);
    }
}
