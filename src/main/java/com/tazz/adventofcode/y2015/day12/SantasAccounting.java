package com.tazz.adventofcode.y2015.day12;

import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.WholeFileReadStrategy;
import reactor.core.publisher.Mono;

public class SantasAccounting {
    private final ParserContext<Integer> parserContext;
    private final ParserContext<Integer> parserRedContext;

    public SantasAccounting() {
        parserContext = new ParserContext<>(new NumberParser(), new WholeFileReadStrategy());
        parserRedContext = new ParserContext<>(new NumberRedParser(), new WholeFileReadStrategy());
    }


    public Mono<Integer> getSum(String classpathFile) {
        return parserContext.parseClasspathFile(classpathFile)
                .reduce(0, Integer::sum);
    }

    public Mono<Integer> getSumIgnoreRed(String classpathFile) {
        return parserRedContext.parseClasspathFile(classpathFile)
                .reduce(0, Integer::sum);
    }

    
}
