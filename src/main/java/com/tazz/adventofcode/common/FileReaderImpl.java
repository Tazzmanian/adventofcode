package com.tazz.adventofcode.common;

import org.springframework.core.io.ClassPathResource;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderImpl implements FileReader {
    @Override
    public Mono<String> readAsString(Path path) {
        return Mono.fromCallable(() -> Files.readString(path));
    }
}
