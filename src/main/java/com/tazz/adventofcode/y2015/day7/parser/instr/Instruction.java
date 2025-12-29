package com.tazz.adventofcode.y2015.day7.parser.instr;

import com.tazz.adventofcode.y2015.day7.parser.operand.Lit;
import com.tazz.adventofcode.y2015.day7.parser.operand.Operand;
import com.tazz.adventofcode.y2015.day7.parser.operand.Wire;

import java.util.Map;
import java.util.OptionalInt;

public sealed interface Instruction permits And, Assign, LShift, Not, Or, RShift {
    OptionalInt tryEval(Map<String, Integer> known);

    static OptionalInt op(Operand o, Map<String, Integer> known) {
        return switch (o) {
            case Lit lit -> OptionalInt.of(lit.value() & 0xFFFF);
            case Wire w -> {
                Integer v = known.get(w.name());
                yield v == null ? OptionalInt.empty() : OptionalInt.of(v);
            }
        };
    }
}
