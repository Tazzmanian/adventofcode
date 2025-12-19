package com.tazz.adventofcode.common.readers;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface LineFileReader {
    Flux<String> readAsString(Path path);
}
