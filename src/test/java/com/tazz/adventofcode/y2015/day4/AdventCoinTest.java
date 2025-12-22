package com.tazz.adventofcode.y2015.day4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

import java.security.NoSuchAlgorithmException;

class AdventCoinTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day4/test01.txt, 609043",
            "/2015/day4/input.txt, 254575",
    })
    public void mineFiveZeroes_shouldGetCorrectHexadecimal(String classpathFile, int expected) throws NoSuchAlgorithmException {
        AdventCoin coin = new AdventCoin();
        StepVerifier.create(coin.mineFiveZeros(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day4/input.txt, 1038736",
    })
    public void mineSixZeroes_shouldGetCorrectHexadecimal(String classpathFile, int expected) throws NoSuchAlgorithmException {
        AdventCoin coin = new AdventCoin();
        StepVerifier.create(coin.mineSixZeros(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

}