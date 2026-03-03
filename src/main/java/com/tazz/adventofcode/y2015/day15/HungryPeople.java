package com.tazz.adventofcode.y2015.day15;

import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import reactor.core.publisher.Mono;

import java.util.List;

public class HungryPeople {

    private ParserContext<Ingredient> parserContext;

    public HungryPeople() {
        parserContext = new ParserContext<>(new IngredientParser(), new LineFileReaderImpl());
    }

    public Mono<Long> getBestScore(String filepath, int spoons) {
        Mono<List<Ingredient>> statsMono = parserContext.parseClasspathFile(filepath).collectList();
        return statsMono.map(x -> {
            RecipeGenerator generator = new RecipeGenerator(x, spoons);
            generator.generateRecipes();
            return generator.getBestScore();
        });
    }

    public Mono<Long> getBestScorePerCalories(String filepath, int spoons, int calories) {
        Mono<List<Ingredient>> statsMono = parserContext.parseClasspathFile(filepath).collectList();
        return statsMono.map(x -> {
            RecipeGenerator generator = new RecipeGenerator(x, spoons);
            generator.generateRecipesCalories(calories);
            return generator.getBestScore();
        });
    }
}
