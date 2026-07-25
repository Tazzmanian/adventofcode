package com.tazz.adventofcode.y2015.day16.reader;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import com.tazz.adventofcode.y2015.day16.Aunt;

import reactor.core.publisher.Mono;

@Service
public class AuntDetector {

    private AuntProducer auntProducer;

    private ParserContext<Aunt> parserContext;

    public AuntDetector(AuntProducer auntProducer) {
        this.auntProducer = auntProducer;
        parserContext = new ParserContext<>(new AuntParser(), new LineFileReaderImpl());
    }

    public void loadAunts(String filepath, int spoons) {
        Mono<List<Aunt>> aunts = parserContext.parseClasspathFile(filepath).collectList();
        aunts.subscribe(auntList -> auntList.forEach(aunt -> auntProducer.sendMessage(aunt)));
    }
}
