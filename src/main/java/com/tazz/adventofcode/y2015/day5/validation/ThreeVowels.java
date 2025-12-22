package com.tazz.adventofcode.y2015.day5.validation;

import java.util.List;

public final class ThreeVowels extends NiceListValidator {
    public ThreeVowels() {
        super(List.of("a", "e", "i", "o", "u"));
    }

    @Override
    public boolean isValid(String string) {
        int count = 0;

        for (var c : string.toCharArray()) {
            if (restrictions.contains(String.valueOf(c))) {
                count++;
            }
        }

        return count >= 3;
    }
}
