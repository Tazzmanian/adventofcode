package com.tazz.adventofcode.y2015.day13;

import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import reactor.core.publisher.Mono;

public class TableOrder {
    private final ParserContext<Neighbor> parserContext;

    public TableOrder() {
        this.parserContext = new ParserContext<>(new LikelihoodParser(), new LineFileReaderImpl());
    }

    public Mono<Integer> calculateGreatestLikes(String filePath) {
        return parserContext.parseClasspathFile(filePath)
                .collect(WeightedGraph::new, (g, n) -> g.addEdge(n))
                .map(WeightedGraph::greatestLikes);
    }

    public Mono<Integer> calculateGreatestLikesIncludingMyself(String filePath) {
        return parserContext.parseClasspathFile(filePath)
                .collect(WeightedGraph::new, (g, n) -> g.addEdge(n))
                .map(WeightedGraph::greatestLikesWithMyself);
    }
}
