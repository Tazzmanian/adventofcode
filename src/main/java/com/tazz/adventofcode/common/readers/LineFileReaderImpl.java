package com.tazz.adventofcode.common.readers;

import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LineFileReaderImpl implements FileReadStrategy {
    @Override
    public Flux<String> read(Path path) {
        return Flux.using(
                () -> Files.lines(path),
                Flux::fromStream,
                Stream::close
        );
    }
}
