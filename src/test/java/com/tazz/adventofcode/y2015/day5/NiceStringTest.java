package com.tazz.adventofcode.y2015.day5;

import com.tazz.adventofcode.y2015.day4.AdventCoin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class NiceStringTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day5/test01.txt, 2",
            "/2015/day5/input.txt, 238",
    })
    public void getCountFromTask1(String classpathFile, long expected) throws NoSuchAlgorithmException {
        NiceString nice = new NiceString();
        StepVerifier.create(nice.getCountFromTask1(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day5/test02.txt, 2",
            "/2015/day5/input.txt, 69",
    })
    public void getCountFromTask2(String classpathFile, long expected) throws NoSuchAlgorithmException {
        NiceString nice = new NiceString();
        StepVerifier.create(nice.getCountFromTask2(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

}