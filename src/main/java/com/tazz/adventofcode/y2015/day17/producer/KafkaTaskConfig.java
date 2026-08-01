package com.tazz.adventofcode.y2015.day17.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import com.tazz.adventofcode.y2015.day17.shared.Task;

@Configuration(proxyBeanMethods = false)
public class KafkaTaskConfig {

    private final Environment environment;

    public KafkaTaskConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ProducerFactory<String, Task> taskProducerFactory(
            @Value("${spring.kafka.bootstrap-servers:localhost:9092}") String bootstrapServers) {
        // String bootstrapServers =
        // environment.getProperty("spring.kafka.bootstrap-servers", "localhost:9092");

        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JacksonJsonSerializer.class);
        configs.put(JacksonJsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, Task> kafkaTaskTemplate(ProducerFactory<String, Task> taskProducerFactory) {
        return new KafkaTemplate<>(taskProducerFactory);
    }
}
