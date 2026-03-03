package com.tazz.adventofcode.y2015.day15;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RecipeGenerator {

    private final List<Ingredient> ingredients;
    private final int spoons;
    private long bestScore;

    public void generateRecipes() {
        var current = emptyRecipe();
        backtrack(0, spoons, current);
    }

    public void generateRecipesCalories(int calories) {
        var current = emptyRecipe();
        backtrackCalories(0, spoons, current, calories);
    }

    private Map<Ingredient, Integer> emptyRecipe() {
        return ingredients.stream().collect(Collectors.toMap(i -> i, i -> 0));
    }

    public void backtrack(int idx, int remaining, Map<Ingredient, Integer> current) {
        if (idx == ingredients.size() - 1) {
            var last = ingredients.get(idx);
            current.put(last, remaining);

            var score = new Recipe(new HashMap<>(current)).getScore();
            if (bestScore < score) {
                bestScore = score;
            }
            return;
        }

        var ing = ingredients.get(idx);
        for (int i = 0; i <= remaining ; i++) {
            current.put(ing, i);
            backtrack(idx + 1, remaining - i, current);
        }
    }

    public void backtrackCalories(int idx, int remaining, Map<Ingredient, Integer> current, int maxCalories) {
        if (idx == ingredients.size() - 1) {
            var last = ingredients.get(idx);
            current.put(last, remaining);

            var recipe = new Recipe(new HashMap<>(current));
            Long score = recipe.getScore();
            if (recipe.getCalories() == maxCalories
                    && bestScore < score) {
                bestScore = score;
            }
            return;
        }

        var ing = ingredients.get(idx);
        for (int i = 0; i <= remaining ; i++) {
            current.put(ing, i);
            backtrackCalories(idx + 1, remaining - i, current, maxCalories);
        }
    }

}
