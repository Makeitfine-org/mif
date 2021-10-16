/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.kafka.configuration.kafka;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@SuppressWarnings({"unchecked", "argument.type.incompatible"})
@SuppressFBWarnings({"NP_NONNULL_PARAM_VIOLATION", "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
public abstract class EventKafkaConfig<Event> {

    private Class<Event> clazz;

    public EventKafkaConfig(Class<Event> clazz) {
        this.clazz = clazz;
    }

    @Bean
    public ProducerFactory<String, Event> producerFactory(
            @Autowired @Qualifier("producerConfigs") Map<String, Object> producerConfigs) {
        return new DefaultKafkaProducerFactory<>((Map<String, Object>) producerConfigs.get("producerConfigs"));
    }

    @Bean
    public KafkaTemplate<String, Event> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory(null));
    }

    @Bean
    public ConsumerFactory<String, Event> consumerFactory(
            @Autowired @Qualifier("consumerConfigs") Map<String, Object> consumerConfigs) {

        return new DefaultKafkaConsumerFactory<>((Map<String, Object>) consumerConfigs.get("consumerConfigs"),
                new StringDeserializer(), new JsonDeserializer<>(clazz)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Event> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(null));
        return factory;
    }
}
