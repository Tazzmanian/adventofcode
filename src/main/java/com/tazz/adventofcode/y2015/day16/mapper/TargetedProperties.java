package com.tazz.adventofcode.y2015.day16.mapper;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TargetedProperties {

    @Bean("targetAunt")
    public HashMap<String, Integer> targetAuntProperties() {
        HashMap<String, Integer> targetAuntProperties = new HashMap<>();
        targetAuntProperties.put("children", 3);
        targetAuntProperties.put("cats", 7);
        targetAuntProperties.put("samoyeds", 2);
        targetAuntProperties.put("pomeranians", 3);
        targetAuntProperties.put("akitas", 0);
        targetAuntProperties.put("vizslas", 0);
        targetAuntProperties.put("goldfish", 5);
        targetAuntProperties.put("trees", 3);
        targetAuntProperties.put("cars", 2);
        targetAuntProperties.put("perfumes", 1);
        return targetAuntProperties;
    }
}
