package com.tazz.adventofcode.y2015.day3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

class HouseLocationsTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day3/test01.txt, 2",
            "/2015/day3/test02.txt, 4",
            "/2015/day3/test03.txt, 2",
            "/2015/day3/input.txt, 2565",

    })
    public void visitedHouseCounter_shouldGetCorrectVisits(String classpathFile, long expected) {
        var wrapper = new HouseLocations();
        StepVerifier.create(wrapper.visitedHouseCounter(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day3/test02.txt, 3",
            "/2015/day3/test03.txt, 11",
            "/2015/day3/test04.txt, 3",
            "/2015/day3/input.txt, 2639",

    })
    public void visitedHouseCounterWithRobo_shouldGetCorrectVisits(String classpathFile, long expected) {
        var wrapper = new HouseLocations();
        StepVerifier.create(wrapper.visitedHouseCounterWithRobo(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

}