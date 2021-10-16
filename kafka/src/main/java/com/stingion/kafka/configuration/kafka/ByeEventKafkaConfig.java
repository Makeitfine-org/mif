/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.kafka.configuration.kafka;

import com.stingion.kafka.event.ByeEvent;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;

@SuppressFBWarnings({"SIC_INNER_SHOULD_BE_STATIC_ANON", "UMAC_UNCALLABLE_METHOD_OF_ANONYMOUS_CLASS"})
@Slf4j
@Configuration
public class ByeEventKafkaConfig extends EventKafkaConfig<ByeEvent> {

    public ByeEventKafkaConfig() {
        super(ByeEvent.class);
    }

    @SuppressWarnings("CPD-START")
    @Profile("!test")
    @Bean("byeTopic")
    public NewTopic createTopic(@Value("${spring.kafka.topic.bye}") String topicName) {
        return new NewTopic(topicName, 2, (short) 2);
    }

    @Profile("!test")
    @Bean("byeListener")
    @DependsOn("byeTopic")
    public Object listener() {
        return new Object() {

            @KafkaListener(topics = "${spring.kafka.topic.bye}", groupId = "${spring.kafka.group-id}")
            public void consume(ByeEvent bye) {
                log.info("Consumed bye message () -> {}", bye);
            }
        };
    }
}
