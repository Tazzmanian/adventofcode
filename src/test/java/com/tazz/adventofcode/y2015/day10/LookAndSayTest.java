package com.tazz.adventofcode.y2015.day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LookAndSayTest {
    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "1, 11",
            "11, 21",
            "21, 1211",
            "1211, 111221",
            "111221, 312211",

    })
    public void test_mutate(String init, String expected) {
        Assertions.assertEquals(expected, LookAndSay.mutate(init));
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={2}")
    @CsvSource({
            "1, 5, 6",
            "1321131112, 40, 492982",
            "1321131112, 50, 6989950",

    })
    public void test_mutator(String init, int iterations, int expected) {
        Assertions.assertEquals(expected, LookAndSay.mutator(init, iterations));
    }
}