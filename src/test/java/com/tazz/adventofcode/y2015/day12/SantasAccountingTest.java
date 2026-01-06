package com.tazz.adventofcode.y2015.day12;

import com.tazz.adventofcode.y2015.day9.SantasPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class SantasAccountingTest {

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day12/test01.txt, 6",
            "/2015/day12/test02.txt, 6",
            "/2015/day12/test03.txt, 3",
            "/2015/day12/test04.txt, 3",
            "/2015/day12/test05.txt, 0",
            "/2015/day12/test06.txt, 0",
            "/2015/day12/test07.txt, 0",
            "/2015/day12/test08.txt, 0",
            "/2015/day12/input.txt, 111754",
    })
    public void test1(String classpathFile, int expected) {
        SantasAccounting path = new SantasAccounting();
        StepVerifier.create(path.getSum(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

    @ParameterizedTest(name = "{index} => file={0}, expected={1}")
    @CsvSource({
            "/2015/day12/test09.txt, 6",
            "/2015/day12/test10.txt, 4",
            "/2015/day12/test11.txt, 0",
            "/2015/day12/test12.txt, 6",
            "/2015/day12/input.txt, 65402",
    })
    public void test2(String classpathFile, int expected) {
        SantasAccounting path = new SantasAccounting();
        StepVerifier.create(path.getSumIgnoreRed(classpathFile))
                .expectNext(expected)
                .verifyComplete();
    }

}