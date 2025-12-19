package com.tazz.adventofcode.y2015.day1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class FloorCounterTaskTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day1/test1.txt, 0",
            "/2015/day1/test2.txt, 0",
            "/2015/day1/test3.txt, 3",
            "/2015/day1/test4.txt, 3",
            "/2015/day1/test5.txt, 3",
            "/2015/day1/test6.txt, -1",
            "/2015/day1/test7.txt, -1",
            "/2015/day1/test8.txt, -3",
            "/2015/day1/test9.txt, -3",
            "/2015/day1/input.txt, 280",

    })
    public void getFlour_shouldGetCorrectFloor(String classpathFile, int expected) {
        var actual = new FloorCounterTask()
                .getFloor(classpathFile)
                .doOnNext(result -> log.info("Floor = {}", result))
                .block();
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day1/test10.txt, 1",
            "/2015/day1/test11.txt, 5",
            "/2015/day1/input.txt, 1797",

    })
    public void firstBasement_shouldGetFirstBasementEncounter(String classpathFile, int expected) {
        var actual = new FloorCounterTask()
                .firstBasement(classpathFile)
                .doOnNext(result -> log.info("Index = {}", result))
                .block();
        assertEquals(expected, actual);
    }

}