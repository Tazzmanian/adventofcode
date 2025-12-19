package com.tazz.adventofcode.common.readers;

import reactor.core.publisher.Mono;

import java.nio.file.Path;

public interface WholeFileReader {

    Mono<String> readAsString(Path path);
}
