package com.tazz.adventofcode.y2015.day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

class SantaListTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day8/test01.txt, 12",
            "/2015/day8/input.txt, 1350",
    })
    public void testSantaList_task1(String classpathFile, int expected) {
        SantaList list = new SantaList();
        StepVerifier.create(list.getGiftsSpace(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day8/test01.txt, 19",
            "/2015/day8/input.txt, 2085",
    })
    public void testSantaList_task2(String classpathFile, int expected) {
        SantaList list = new SantaList();
        StepVerifier.create(list.getGiftsEncodedSpace(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

}