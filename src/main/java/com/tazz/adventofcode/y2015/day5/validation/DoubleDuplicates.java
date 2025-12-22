package com.tazz.adventofcode.y2015.day5.validation;

import java.util.List;

public final class DoubleDuplicates extends NiceListValidator {
    public DoubleDuplicates() {
        super(List.of());
    }

    @Override
    public boolean isValid(String string) {
        for (int i = 0; i < string.length() - 2; i++) {
            for (int j = 2 + i; j < string.length() - 1; j++) {
                if (string.charAt(i) == string.charAt(j) && string.charAt(i + 1) == string.charAt(j + 1))
                    return true;
            }
        }
        return false;
    }
}
