package com.tazz.adventofcode.y2015.day7.parser.instr;

import com.tazz.adventofcode.y2015.day7.parser.operand.Lit;
import com.tazz.adventofcode.y2015.day7.parser.operand.Operand;

import java.util.Map;
import java.util.OptionalInt;

public record RShift(Operand a, int n) implements Instruction {

    @Override
    public OptionalInt tryEval(Map<String, Integer> known) {
        var av = Instruction.op(a, known);
        if (av.isEmpty()) return OptionalInt.empty();
        return OptionalInt.of((av.getAsInt() >> n) & 0xFFFF);
    }
}
