package com.tazz.adventofcode.common;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface Parser<T> {

    Flux<T> parse(String input);
}
