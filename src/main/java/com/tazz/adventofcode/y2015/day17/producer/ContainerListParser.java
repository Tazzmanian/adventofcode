package com.tazz.adventofcode.y2015.day17.producer;

import java.util.List;

import org.springframework.util.StringUtils;

import com.tazz.adventofcode.common.Parser;

import reactor.core.publisher.Flux;

public class ContainerListParser implements Parser<Integer> {

    @Override
    public Flux<Integer> parse(Flux<String> lines) {
        return lines.filter(StringUtils::hasText)
                .map(String::trim)
                .map(Integer::parseInt);
    }

}
