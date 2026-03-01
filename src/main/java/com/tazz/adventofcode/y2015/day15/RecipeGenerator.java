package com.tazz.adventofcode.y2015.day15;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class RecipeGenerator {

    private final List<Ingredient> ingredients;
    private final int spoons;
    private long bestScore;

    public void generateRecipes() {
        var current = emptyRecipe();
        backtrack(0, spoons, current);
    }

    private Map<Ingredient, Integer> emptyRecipe() {
        return ingredients.stream().collect(Collectors.toMap(i -> i, i -> 0));
    }

    public void backtrack(int idx, int remaining, Map<Ingredient, Integer> current) {
        if (idx == ingredients.size()) {
            var last = ingredients.get(idx);
            current.put(last, remaining);

            var score = new Recipe(new HashMap<>(current)).getScore();
            if (bestScore < score) {
                bestScore = score;
            }
            return;
        }

        var ing = ingredients.get(idx);
        for (int i = 0; i <= spoons ; i++) {
            current.put(ing, i);
            backtrack(idx, remaining - 1, current);
        }
    }

}
