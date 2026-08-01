package com.tazz.adventofcode.y2015.day17.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultShareConsumerFactory;
import org.springframework.kafka.core.ShareConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.stereotype.Component;

import com.tazz.adventofcode.y2015.day17.shared.Task;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaTaskConsumerConfig {

    private final WorkScheduler scheduler;

    @KafkaListener(topics = "eggnog-containers", groupId = "task1", containerFactory = "kafkaListenerContainerFactory", concurrency = "10")
    public void listen(Task task) {
        System.out.println("Received message: " + task);
    }

    @Bean
    public ShareConsumerFactory<String, Task> taskConsumerFactory(
            @Value("${spring.kafka.bootstrap-servers:localhost:9092}") String bootstrapServers) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
        props.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, Task.class);
        props.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultShareConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Task> kafkaListenerContainerFactory(
            ConsumerFactory<String, Task> taskConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Task> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(taskConsumerFactory);
        return factory;
    }
}
