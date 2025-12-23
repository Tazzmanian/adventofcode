package com.tazz.adventofcode.y2015.day6;

import com.tazz.adventofcode.common.Parser;
import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.FileReadStrategy;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Lights {
    private final boolean[][] grid;
    private final int[][] brightnessGrid;
    private final ParserContext<LineCommand> parserContext;

    public Lights(int gridSize) {
        grid = new boolean[gridSize][gridSize];
        brightnessGrid = new int[gridSize][gridSize];
        FileReadStrategy fileReader = new LineFileReaderImpl();
        Parser<LineCommand> parser = new CommandParser();
        this.parserContext = new ParserContext<>(parser, fileReader);
    }

    public Mono<Long> lightsOn(String classpath) {
        return parserContext.parseClasspathFile(classpath)
                .concatMap(cmd ->
                        Mono.fromRunnable(() -> cmd.execute(grid))).subscribeOn(Schedulers.parallel())
                .doOnError(e -> log.error("Error: ", e))
                .then(Mono.fromSupplier(this::countLit));
    }

    public Mono<Long> brightnessOn(String classpath) {
        return parserContext.parseClasspathFile(classpath)
                .concatMap(cmd ->
                        Mono.fromRunnable(() -> cmd.execute(brightnessGrid))).subscribeOn(Schedulers.parallel())
                .doOnError(e -> log.error("Error: ", e))
                .then(Mono.fromSupplier(this::countBrightness));
    }

    private long countLit() {
        long count = 0L;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y]) count++;
            }
        }
        return count;
    }

    private long countBrightness() {
        long count = 0L;
        for (int x = 0; x < brightnessGrid.length; x++) {
            for (int y = 0; y < brightnessGrid[x].length; y++) {
                count += brightnessGrid[x][y];
            }
        }
        return count;
    }
}
