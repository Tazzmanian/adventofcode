package com.tazz.adventofcode.y2015.day1;

import com.tazz.adventofcode.common.*;
import com.tazz.adventofcode.common.readers.FileReadStrategy;
import com.tazz.adventofcode.common.readers.WholeFileReadStrategy;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Slf4j
public class FloorCounterTask {

    private final ParserContext<Character> parserContext;

    public FloorCounterTask() {
        FileReadStrategy fileReader = new WholeFileReadStrategy();
        Parser<Character> floorParser = new FloorParser();
        this.parserContext = new ParserContext<>(floorParser, fileReader);
    }


    public Mono<Integer> getFloor(String classpathFile) {
        return parserContext.parseClasspathFile(classpathFile)
                .reduce(0, (acc, ch) -> acc + (ch == '(' ? 1 : -1));
    }

    public Mono<Long> firstBasement(String classpathFile) {
        var chars = parserContext.parseClasspathFile(classpathFile);
        return chars
                .map(ch -> ch == '(' ? 1 : -1)
                .scan(0, Integer::sum)
                .index()
                .filter(tupple -> tupple.getT2() == -1)
                .map(Tuple2::getT1)
                .next();
    }
}
