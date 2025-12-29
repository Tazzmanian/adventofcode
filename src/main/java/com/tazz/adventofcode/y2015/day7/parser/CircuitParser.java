package com.tazz.adventofcode.y2015.day7.parser;

import com.tazz.adventofcode.common.Parser;
import com.tazz.adventofcode.y2015.day7.parser.instr.*;
import com.tazz.adventofcode.y2015.day7.parser.operand.Lit;
import com.tazz.adventofcode.y2015.day7.parser.operand.Operand;
import com.tazz.adventofcode.y2015.day7.parser.operand.Wire;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class CircuitParser implements Parser<Parsed> {
    private static final Pattern ASSIGN = Pattern.compile("^(\\w+) -> (\\w+)$");
    private static final Pattern AND    = Pattern.compile("^(\\w+) AND (\\w+) -> (\\w+)$");
    private static final Pattern OR     = Pattern.compile("^(\\w+) OR (\\w+) -> (\\w+)$");
    private static final Pattern LSH    = Pattern.compile("^(\\w+) LSHIFT (\\d+) -> (\\w+)$");
    private static final Pattern RSH    = Pattern.compile("^(\\w+) RSHIFT (\\d+) -> (\\w+)$");
    private static final Pattern NOT    = Pattern.compile("^NOT (\\w+) -> (\\w+)$");

    public Parsed parse(String line) {
        Matcher m;

        if ((m = AND.matcher(line)).matches())
            return new Parsed(m.group(3), new And(op(m.group(1)), op(m.group(2))));

        if ((m = OR.matcher(line)).matches())
            return new Parsed(m.group(3), new Or(op(m.group(1)), op(m.group(2))));

        if ((m = LSH.matcher(line)).matches())
            return new Parsed(m.group(3), new LShift(op(m.group(1)), Integer.parseInt(m.group(2))));

        if ((m = RSH.matcher(line)).matches())
            return new Parsed(m.group(3), new RShift(op(m.group(1)), Integer.parseInt(m.group(2))));

        if ((m = NOT.matcher(line)).matches())
            return new Parsed(m.group(2), new Not(op(m.group(1))));

        if ((m = ASSIGN.matcher(line)).matches())
            return new Parsed(m.group(2), new Assign(op(m.group(1))));

        throw new IllegalArgumentException("Bad line: " + line);
    }

    private Operand op(String token) {
        if (token.chars().allMatch(Character::isDigit)) return new Lit(Integer.parseInt(token));
        return new Wire(token);
    }

    @Override
    public Flux<Parsed> parse(Flux<String> source) {
        return source.map(this::parse);
    }
}
