package com.tazz.adventofcode.y2015.day7;

import com.tazz.adventofcode.common.Parser;
import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.FileReadStrategy;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import com.tazz.adventofcode.y2015.day7.parser.CircuitParser;
import com.tazz.adventofcode.y2015.day7.parser.Parsed;
import com.tazz.adventofcode.y2015.day7.parser.instr.Instruction;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Gate {

    private final ParserContext<Parsed> parserContext;

    public Gate() {
        FileReadStrategy fileReader = new LineFileReaderImpl();
        Parser<Parsed> parser = new CircuitParser();
        parserContext = new ParserContext<>(parser, fileReader);
    }

    public Mono<Integer> signalOn(String classpath, String wire) {
        return parserContext.parseClasspathFile(classpath)
                .collectMap(Parsed::out, Parsed::instr)
                .map(program -> solve(wire, program));
    }

    static int solve(String target, Map<String, Instruction> program) {
        Map<String, Integer> known = new HashMap<>();
        Map<String, Instruction> pending = new HashMap<>(program);

        while (true) {
            if (known.containsKey(target))
                return known.get(target);

            int before = pending.size();

            Iterator<Map.Entry<String, Instruction>> it = pending.entrySet().iterator();
            boolean progressed = false;

            while (it.hasNext()) {
                var e = it.next();
                var out = e.getKey();
                var instr = e.getValue();

                var res = instr.tryEval(known);
                if (res.isPresent()) {
                    known.put(out, res.getAsInt());
                    it.remove();
                    progressed = true;
                }
            }

            if (!progressed) {
                throw new IllegalStateException(
                        "Stuck: cannot resolve remaining wires: " + pending.keySet()
                );
            }

            // small optimization: if pending didn't shrink, we'd have thrown above
            if (pending.size() == before) break;
        }

        throw new IllegalStateException("Unreachable");
    }

    public Mono<Integer> signalOn(String classpath, String wire, Map<String, Integer> overrides) {
        return parserContext.parseClasspathFile(classpath)
                .collectMap(Parsed::out, Parsed::instr)
                .map(program -> solve(wire, program, overrides));
    }

    static int solve(String target, Map<String, Instruction> program, Map<String, Integer> overrides) {
        Map<String, Integer> known = new HashMap<>();
        known.putAll(overrides);
        Map<String, Instruction> pending = new HashMap<>(program);

        while (true) {
            if (known.containsKey(target))
                return known.get(target);

            int before = pending.size();

            Iterator<Map.Entry<String, Instruction>> it = pending.entrySet().iterator();
            boolean progressed = false;

            while (it.hasNext()) {
                var e = it.next();
                var out = e.getKey();
                var instr = e.getValue();

                var res = instr.tryEval(known);
                if (res.isPresent()) {
                    if (!known.containsKey(out)) {
                        known.put(out, res.getAsInt());
                        it.remove();
                    }
                    progressed = true;
                }
            }

            if (!progressed) {
                throw new IllegalStateException(
                        "Stuck: cannot resolve remaining wires: " + pending.keySet()
                );
            }

            // small optimization: if pending didn't shrink, we'd have thrown above
            if (pending.size() == before) break;
        }

        throw new IllegalStateException("Unreachable");
    }
}
