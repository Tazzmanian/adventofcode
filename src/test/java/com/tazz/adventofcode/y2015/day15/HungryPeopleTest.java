package com.tazz.adventofcode.y2015.day15;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

class HungryPeopleTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day15/test01.txt, 100, 62842880",
            "/2015/day15/input.txt, 100, 21367368",
    })
    public void task1(String classpathFile, int spoons, long expected) {
        HungryPeople hungryPeople = new HungryPeople();
        StepVerifier.create(hungryPeople.getBestScore(classpathFile, spoons))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day15/test01.txt, 100, 500, 57600000",
            "/2015/day15/input.txt, 100, 500, 1766400",
    })
    public void task2(String classpathFile, int spoons, int calories, long expected) {
        HungryPeople hungryPeople = new HungryPeople();
        StepVerifier.create(hungryPeople.getBestScorePerCalories(classpathFile, spoons, calories))
                .expectNext(expected)
                .verifyComplete();
    }

}