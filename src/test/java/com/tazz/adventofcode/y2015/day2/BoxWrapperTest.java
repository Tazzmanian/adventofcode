package com.tazz.adventofcode.y2015.day2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

class BoxWrapperTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day2/test01.txt, 58",
            "/2015/day2/test02.txt, 43",
            "/2015/day2/input.txt, 1606483",

    })
    public void totalWrapper_shouldGetCorrectArea(String classpathFile, int expected) {
        var wrapper = new BoxWrapper();
        StepVerifier.create(wrapper.totalWrapper(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day2/test01.txt, 34",
            "/2015/day2/test02.txt, 14",
            "/2015/day2/input.txt, 3842356",

    })
    public void totalRibbon_shouldGetCorrectLength(String classpathFile, int expected) {
        var wrapper = new BoxWrapper();
        StepVerifier.create(wrapper.totalRibbon(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }
}