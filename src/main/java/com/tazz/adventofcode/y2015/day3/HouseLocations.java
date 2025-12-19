package com.tazz.adventofcode.y2015.day3;

import com.tazz.adventofcode.common.Parser;
import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.FileReadStrategy;
import com.tazz.adventofcode.common.readers.WholeFileReadStrategy;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.HashSet;
import java.util.Set;

public class HouseLocations {
    private final ParserContext<Movement> parserContext;
    public final Position origin;

    public HouseLocations() {
        origin = new Position(0, 0);
        FileReadStrategy fileReader = new WholeFileReadStrategy();
        Parser<Movement> parser = new MovementParser();
        this.parserContext = new ParserContext<>(parser, fileReader);
    }

    public Mono<Long> visitedHouseCounter(String classpathFile) {
        return parserContext.parseClasspathFile(classpathFile)
                .scan(origin, (pos, movement) -> movement.updatedPosition(pos))
                .distinct()
                .count();
    }

    public Mono<Long> visitedHouseCounterWithRobo(String classpathFile) {
        return parserContext.parseClasspathFile(classpathFile)
                .index()                                        // Flux<Tuple2<Long, Movement>>
                .groupBy(t -> t.getT1() % 2)                    // key: 0 (even) / 1 (odd)
                .flatMap(grouped -> grouped
                        .map(Tuple2::getT2)                    // Movement
                        .scan(origin, (pos, m) -> m.updatedPosition(pos)) // Flux<Position> per player (seeded)
                )
                .distinct()    // dedupe positions across both players (origin counted once)
                .count();       // Mono<Long>
    }
}
