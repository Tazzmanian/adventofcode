package com.tazz.adventofcode.y2015.day5.validation;

import java.util.List;

public final class DuplicatesSkipMiddle extends NiceListValidator {
    public DuplicatesSkipMiddle() {
        super(List.of());
    }

    @Override
    public boolean isValid(String string) {
        for (int i = 1; i < string.length() - 1; i++) {
            if (string.charAt(i - 1) == string.charAt(i + 1))
                return true;
        }
        return false;
    }
}
