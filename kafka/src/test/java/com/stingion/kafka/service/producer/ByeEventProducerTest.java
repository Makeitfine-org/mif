/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.kafka.service.producer;

import com.stingion.kafka.event.ByeEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.test.context.EmbeddedKafka;

@EmbeddedKafka(topics = "${spring.kafka.topic.bye}")
public class ByeEventProducerTest extends KafkaProducerTestInitializer<ByeEvent> {

    @Autowired
    private Producer<ByeEvent> producer;

    public ByeEventProducerTest(@Value("${spring.kafka.topic.bye}") String topicName) {
        super(topicName);
    }

    @Test
    public void kafkaSetup_WithTopic_EnsureSendMessageIsReceived() throws InterruptedException {
        ByeEvent message = new ByeEvent("Bye, here!");
        producer.sendMessage(message);

        assertStringPolled(message);
    }
}
