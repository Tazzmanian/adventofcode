package com.tazz.adventofcode.common;

import org.springframework.core.io.ClassPathResource;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

public interface FileReader {

    Mono<String> readAsString(Path path);
}
