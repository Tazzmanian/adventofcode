package com.tazz.adventofcode.y2015.day9;

import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import reactor.core.publisher.Mono;

public class SantasPath {

    private final ParserContext<Distance> parserContext;

    public SantasPath() {
        parserContext = new ParserContext<>(new DistanceParser(), new LineFileReaderImpl());
    }

    public Mono<Integer> shortest(String classpathFile) {
        return parserContext.parseClasspathFile(classpathFile)
                .collect(WeightedGraph::new, (g, d) -> ((WeightedGraph)g).addEdge(d.cityA(), d.cityB(), d.km(), false))
                .map(WeightedGraph::shortestHamiltonianPath);
     }

    public Mono<Integer> longest(String classpathFile) {
        return parserContext.parseClasspathFile(classpathFile)
                .collect(WeightedGraph::new, (g, d) -> ((WeightedGraph)g).addEdge(d.cityA(), d.cityB(), d.km(), false))
                .map(WeightedGraph::longestHamiltonianPath);
    }

}
