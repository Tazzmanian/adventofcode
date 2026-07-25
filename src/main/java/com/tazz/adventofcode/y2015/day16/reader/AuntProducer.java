package com.tazz.adventofcode.y2015.day16.reader;

import java.util.HashMap;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tazz.adventofcode.y2015.day16.Aunt;

@Service
public class AuntProducer {

    private final KafkaTemplate<String, Aunt> kafkaTemplate;

    public AuntProducer(KafkaTemplate<String, Aunt> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Aunt aunt) {
        kafkaTemplate.send("aunt-sue", aunt);
    }
}
