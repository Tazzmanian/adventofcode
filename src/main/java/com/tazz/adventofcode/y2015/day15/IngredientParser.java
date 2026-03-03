package com.tazz.adventofcode.y2015.day15;

import com.tazz.adventofcode.common.Parser;
import reactor.core.publisher.Flux;

public class IngredientParser implements Parser<Ingredient> {
    @Override
    public Flux<Ingredient> parse(Flux<String> source) {
        return source.map(x -> {
            String[] arr = x.split(":");
            String name = arr[0];
            String properties = arr[1];
            arr = properties.split(",");
            return Ingredient.builder()
                    .name(name)
                    .capacity(Integer.parseInt(arr[0].trim().split(" ")[1]))
                    .durability(Integer.parseInt(arr[1].trim().split(" ")[1]))
                    .flavor(Integer.parseInt(arr[2].trim().split(" ")[1]))
                    .texture(Integer.parseInt(arr[3].trim().split(" ")[1]))
                    .calories(Integer.parseInt(arr[4].trim().split(" ")[1]))
                    .build();
        });
    }
}
