package com.tazz.adventofcode.y2015.day5.validation;

import java.util.Collections;
import java.util.List;

public final class Duplicates extends NiceListValidator {
    public Duplicates() {
        super(Collections.emptyList());
    }

    @Override
    public boolean isValid(String string) {
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i - 1) == string.charAt(i)) {
                return true;
            }
        }
        return false;
    }
}
