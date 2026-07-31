package com.tazz.adventofcode.y2015.day16.mapper;

import java.util.HashMap;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tazz.adventofcode.y2015.day16.Aunt;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
@Setter
public class AuntComparator {

    private final HashMap<String, Integer> targetAunt;

    public String compareAunt(Aunt aunt) {
        return aunt.getAttributes().entrySet().stream()
                .allMatch(entry -> targetAunt.containsKey(entry.getKey())
                        && targetAunt.get(entry.getKey()).equals(entry.getValue()))
                                ? aunt.getName()
                                : null;
    }

    public String compareAunt_v2(Aunt aunt) {
        var attributes = aunt.getAttributes();

        for (var entry : attributes.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (!targetAunt.containsKey(key)) {
                continue; // Ignore unknown attributes
            }

            Integer targetValue = targetAunt.get(key);

            switch (key) {
                case "cats", "trees" -> {
                    if (value <= targetValue) {
                        return null; // Not a match
                    }
                }
                case "pomeranians", "goldfish" -> {
                    if (value >= targetValue) {
                        return null; // Not a match
                    }
                }
                default -> {
                    if (!value.equals(targetValue)) {
                        return null; // Not a match
                    }
                }
            }
        }
        return aunt.getName(); // All attributes match
    }

}
