/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.kafka.service.producer;

import com.stingion.kafka.event.MessageEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.test.context.EmbeddedKafka;

@EmbeddedKafka(topics = "${spring.kafka.topic.message}")
public class MessageEventProducerTest extends KafkaProducerTestInitializer<MessageEvent> {

    @Autowired
    private Producer<MessageEvent> producer;

    public MessageEventProducerTest(@Value("${spring.kafka.topic.message}") String topicName) {
        super(topicName);
    }

    @Test
    public void kafkaSetup_WithTopic_EnsureSendMessageIsReceived() throws InterruptedException {
        MessageEvent message = new MessageEvent("Message is it!");
        producer.sendMessage(message);

        assertStringPolled(message);
    }
}
