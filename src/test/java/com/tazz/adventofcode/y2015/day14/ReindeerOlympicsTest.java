package com.tazz.adventofcode.y2015.day14;

import com.tazz.adventofcode.y2015.day13.TableOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class ReindeerOlympicsTest {
    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day14/test01.txt, 1120, 1000",
            "/2015/day14/input.txt, 2660, 2503",
    })
    public void task1(String classpathFile, int expected, int seconds) {
        ReindeerOlympics olympics = new ReindeerOlympics();
        StepVerifier.create(olympics.getMaxDistanceAfter(classpathFile, seconds))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day14/test01.txt, 689, 1000",
            "/2015/day14/input.txt, 1256, 2503",
    })
    public void task2(String classpathFile, int expected, int seconds) {
        ReindeerOlympics olympics = new ReindeerOlympics();
        StepVerifier.create(olympics.getMaxPoints(classpathFile, seconds))
                .expectNext(expected)
                .verifyComplete();
    }
}