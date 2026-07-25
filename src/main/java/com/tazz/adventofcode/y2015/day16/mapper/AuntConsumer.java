package com.tazz.adventofcode.y2015.day16.mapper;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tazz.adventofcode.y2015.day16.Aunt;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuntConsumer {

    private final AuntComparator auntComparator;

    @KafkaListener(topics = "aunt-sue", groupId = "task1")
    public void listen(Aunt aunt) {
        auntComparator.compareAunt(aunt);
    }
}
