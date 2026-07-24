package com.tazz.adventofcode.y2015.day16.reader;

import java.util.HashMap;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuntProducer {

    private final KafkaTemplate<String, HashMap<String, Integer>> kafkaTemplate;

    public AuntProducer(KafkaTemplate<String, HashMap<String, Integer>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(HashMap<String, Integer> message) {
        kafkaTemplate.send("aunt-sue", message);
    }
}
