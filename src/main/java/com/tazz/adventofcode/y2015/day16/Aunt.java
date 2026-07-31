package com.tazz.adventofcode.y2015.day16;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aunt {
    private String name;
    private Map<String, Integer> attributes;
}