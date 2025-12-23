package com.tazz.adventofcode.y2015.day6;

import com.tazz.adventofcode.common.Parser;
import com.tazz.adventofcode.y2015.day3.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

class CommandParser implements Parser<LineCommand> {
    @Override
    public Flux<LineCommand> parse(Flux<String> source) {
        return source.map(str -> {
            var command = switch (str) {
                case String s when s.startsWith(Command.ON.getText()) -> Command.ON;
                case String s when s.startsWith(Command.OFF.getText()) -> Command.OFF;
                case String s when s.startsWith(Command.TOGGLE.getText()) -> Command.TOGGLE;
                default -> throw new RuntimeException("Unhandled: " + str);
            };

            List<Point> points = Arrays.stream(str.split(" ")).filter(x -> x.contains(",")).map(x -> {
                String[] split = x.split(",");
                return new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }).toList();

            return new LineCommand(command, points.get(0), points.get(1));
        });
    }
}
