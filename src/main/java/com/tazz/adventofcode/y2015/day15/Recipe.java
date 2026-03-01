package com.tazz.adventofcode.y2015.day15;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class Recipe implements Score {
    private final Map<Ingredient, Integer> list;

    public Long getScore() {
        Ingredient ing = new Ingredient();

        for (var data : list.entrySet()) {
            var tempIng = data.getKey();
            var spoons = data.getValue();
            ing.setCapacity(tempIng.getCapacity() * spoons + ing.getCapacity());
            ing.setDurability(tempIng.getDurability() * spoons + ing.getDurability());
            ing.setCalories(tempIng.getCalories() * spoons + ing.getCalories());
            ing.setFlavor(tempIng.getFlavor() * spoons + ing.getFlavor());
            ing.setTexture(tempIng.getTexture() * spoons + ing.getTexture());
        }

        return ing.getScore();
    }
}
