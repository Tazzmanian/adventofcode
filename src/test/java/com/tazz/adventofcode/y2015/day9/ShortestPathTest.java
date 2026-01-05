package com.tazz.adventofcode.y2015.day9;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

class ShortestPathTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day9/test01.txt, 605",
            "/2015/day9/input.txt, 141",
    })
    public void test_shortestPath(String classpathFile, int expected) {
        SantasPath path = new SantasPath();
        StepVerifier.create(path.shortest(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day9/test01.txt, 982",
            "/2015/day9/input.txt, 736",
    })
    public void test_longestPath(String classpathFile, int expected) {
        SantasPath path = new SantasPath();
        StepVerifier.create(path.longest(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

}