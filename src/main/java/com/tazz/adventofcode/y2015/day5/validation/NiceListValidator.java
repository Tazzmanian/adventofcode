package com.tazz.adventofcode.y2015.day5.validation;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public sealed class NiceListValidator permits DoubleDuplicates, Duplicates, DuplicatesSkipMiddle, ForbiddenSequences, ThreeVowels {

    @Setter
    protected List<NiceListValidator> validators;
    protected final List<String> restrictions;

    public boolean isNice(String string) {
        for (var validator : validators) {
            if (!validator.isValid(string)) {
                return false;
            }
        }
        return true;
    }

    protected boolean isValid(String string) {
        throw new RuntimeException("No implementation");
    };
}
