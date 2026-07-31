package com.tazz.adventofcode.y2015.day7;

import com.tazz.adventofcode.y2015.day6.Lights;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GateTest {

        @ParameterizedTest(name = "{index} => file={0}, expected={2}")
        @CsvSource({
                        "/2015/day7/test01.txt, x, 123",
                        "/2015/day7/test01.txt, y, 456",
                        "/2015/day7/test01.txt, d, 72",
                        "/2015/day7/test01.txt, e, 507",
                        "/2015/day7/test01.txt, f, 492",
                        "/2015/day7/test01.txt, g, 114",
                        "/2015/day7/test01.txt, h, 65412",
                        "/2015/day7/test01.txt, i, 65079",
                        "/2015/day7/input.txt, a, 3176",
                        "/2015/day7/input.txt, b, 44430",
        })
        public void signalOn_task1(String classpathFile, String wire, int expected) {
                Gate gate = new Gate();
                StepVerifier.create(gate.signalOn(classpathFile, wire))
                                .expectNext(expected)
                                .verifyComplete();
        }

        @ParameterizedTest(name = "{index} => file={0}, expected={2}")
        @CsvSource({
                        "/2015/day7/test01.txt, x, 123",
                        "/2015/day7/test01.txt, y, 456",
                        "/2015/day7/test01.txt, d, 72",
                        "/2015/day7/test01.txt, e, 507",
                        "/2015/day7/test01.txt, f, 492",
                        "/2015/day7/test01.txt, g, 114",
                        "/2015/day7/test01.txt, h, 65412",
                        "/2015/day7/test01.txt, i, 65079",
                        "/2015/day7/input.txt, a, 14710"
        })
        public void signalOn_task2(String classpathFile, String wire, int expected) {
                Gate gate = new Gate();
                StepVerifier.create(gate.signalOn(classpathFile, wire, Map.of("b", 3176)))
                                .expectNext(expected)
                                .verifyComplete();
        }

}