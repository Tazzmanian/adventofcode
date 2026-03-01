package com.tazz.adventofcode.y2015.day15;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient implements Score {
    private String name;
    private int capacity;
    private int durability;
    private int flavor;
    private int texture;
    private int calories;

    public Long getScore() {
        return (long) Math.max(capacity, 0)
                * Math.max(durability, 0)
                * Math.max(flavor, 0)
                * Math.max(texture, 0);
    }
}
