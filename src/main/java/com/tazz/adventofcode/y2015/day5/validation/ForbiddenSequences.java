package com.tazz.adventofcode.y2015.day5.validation;

import java.util.List;

public final class ForbiddenSequences extends NiceListValidator {
    public ForbiddenSequences() {
        super(List.of("ab", "cd", "pq", "xy"));
    }

    @Override
    public boolean isValid(String string) {
        return restrictions.stream().noneMatch(string::contains);
    }
}
