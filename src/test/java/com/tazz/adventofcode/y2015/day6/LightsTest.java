package com.tazz.adventofcode.y2015.day6;

import com.tazz.adventofcode.y2015.day5.NiceString;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class LightsTest {
    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day6/input.txt, 569999",
    })
    public void lightsOnTask1(String classpathFile, long expected) {
        Lights lights = new Lights(1000);
        StepVerifier.create(lights.lightsOn(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day6/input.txt, 17836115",
    })
    public void brightnessOnTask2(String classpathFile, long expected) {
        Lights lights = new Lights(1000);
        StepVerifier.create(lights.brightnessOn(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

}