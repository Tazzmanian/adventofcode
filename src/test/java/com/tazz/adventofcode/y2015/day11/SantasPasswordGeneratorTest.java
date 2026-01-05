package com.tazz.adventofcode.y2015.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SantasPasswordGeneratorTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "abcdefgh, abcdffaa",
            "ghijklmn, ghjaabcc",
            "hxbxwxba, hxbxxyzz",
            "hxbxxyzz, hxcaabcc",

    })
    public void test_update(String old, String expected) {
        SantasPasswordGenerator generator = new SantasPasswordGenerator();
        Assertions.assertEquals(expected, generator.update(old));
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "ghijklmn, false",

    })
    public void test_restrictedCharacters(String old, boolean expected) {
        SantasPasswordGenerator generator = new SantasPasswordGenerator();
        Assertions.assertEquals(expected, generator.checkAndUpdate(old.toCharArray()));
    }

}