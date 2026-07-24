package com.tazz.adventofcode.y2015.day16.mapper;

import java.util.HashMap;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuntConsumer {

    @KafkaListener(topics = "aunt-sue", groupId = "task1")
    public void listen(HashMap<String, Integer> message) {
        System.out.println("Received message: " + message);
    }
}
