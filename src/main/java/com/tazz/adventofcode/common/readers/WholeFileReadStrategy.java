package com.tazz.adventofcode.common.readers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;

public class WholeFileReadStrategy implements FileReadStrategy {
    @Override
    public Flux<String> read(Path path) {
        return Mono.fromCallable(() -> Files.readString(path)).flux();
    }
}
