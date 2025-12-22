package com.tazz.adventofcode.y2015.day5;

import com.tazz.adventofcode.common.Parser;
import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.FileReadStrategy;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import com.tazz.adventofcode.common.readers.WholeFileReadStrategy;
import com.tazz.adventofcode.y2015.day3.Movement;
import com.tazz.adventofcode.y2015.day3.MovementParser;
import com.tazz.adventofcode.y2015.day3.Position;
import com.tazz.adventofcode.y2015.day5.validation.*;
import lombok.Setter;
import reactor.core.publisher.Mono;

import java.util.List;

public class NiceString {

    private final ParserContext<String> parserContext;
    private NiceListValidator validator;

    public NiceString() {
        FileReadStrategy fileReader = new LineFileReaderImpl();
        Parser<String> parser = new ListParser();
        this.parserContext = new ParserContext<>(parser, fileReader);
        validator = new NiceListValidator(List.of());
    }

    public Mono<Long> getCountFromTask1(String classpath) {
        validator.setValidators(List.of(new ThreeVowels(), new Duplicates(), new ForbiddenSequences()));
        return parserContext.parseClasspathFile(classpath)
                .filter(x -> validator.isNice(x))
                .count();
    }

    public Mono<Long> getCountFromTask2(String classpath) {
        validator.setValidators(List.of(new DoubleDuplicates(), new DuplicatesSkipMiddle()));
        return parserContext.parseClasspathFile(classpath)
                .filter(x -> validator.isNice(x))
                .count();
    }
}
