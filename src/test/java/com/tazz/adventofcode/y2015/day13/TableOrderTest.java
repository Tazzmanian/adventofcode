package com.tazz.adventofcode.y2015.day13;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

class TableOrderTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day13/test1.txt, 330",
            "/2015/day13/input.txt, 709",
    })
    public void task1(String classpathFile, int expected) {
        TableOrder tableOrder = new TableOrder();
        StepVerifier.create(tableOrder.calculateGreatestLikes(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day13/test1.txt, 286",
            "/2015/day13/input.txt, 668",
    })
    public void task2(String classpathFile, int expected) {
        TableOrder tableOrder = new TableOrder();
        StepVerifier.create(tableOrder.calculateGreatestLikesIncludingMyself(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

}