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
        if (aunt.getAttributes().equals(targetAunt)) {
            System.out.println("Found matching aunt: " + aunt.getName());
            return aunt.getName();
        }
        return null;
    }

}
