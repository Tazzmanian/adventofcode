package com.tazz.adventofcode.y2015.day10;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LookAndSay {

    public static int mutator(String init, int iterations) {
        String str = init;

        for (int i = 0; i < iterations; i++) {
            str = mutate(str);
        }

        return str.length();
    }

    public static String mutate(String init) {
        int count = 0;
        Character current = init.charAt(0);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < init.length(); i++) {
            if (count == 0) {
                current = init.charAt(i);
                count++;
                continue;
            }

            if (current == init.charAt(i)) {
                count++;
                continue;
            }

            sb.append(count).append(current);
            current = init.charAt(i);
            count = 1;
        }

        sb.append(count).append(current);

        return sb.toString();
    }
}
