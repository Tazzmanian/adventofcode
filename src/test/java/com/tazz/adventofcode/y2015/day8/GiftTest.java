package com.tazz.adventofcode.y2015.day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GiftTest {


    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "\"\", 2",
            "\"abc\", 2",
            "\"aaa\\\"aaa\", 3",
            "\"\\x27\", 5",
    })
    public void size_shouldReturnCorrectSize(String literal, int size) {
        var gift = new Gift(literal);
        assertEquals(size, gift.size());
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "\"\", 4",
            "\"abc\", 4",
            "\"aaa\\\"aaa\", 6",
            "\"\\x27\", 5",
    })
    public void encodedSize_shouldReturnCorrectSize(String literal, int size) {
        var gift = new Gift(literal);
        assertEquals(size, gift.encodedSize());
    }
}