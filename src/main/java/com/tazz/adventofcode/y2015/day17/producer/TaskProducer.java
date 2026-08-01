package com.tazz.adventofcode.y2015.day17.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tazz.adventofcode.y2015.day17.shared.Task;

@Service
public class TaskProducer {

    private final KafkaTemplate<String, Task> kafkaTemplate;

    public TaskProducer(KafkaTemplate<String, Task> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Task task) {
        kafkaTemplate.send("eggnog-containers", task);
    }
}
