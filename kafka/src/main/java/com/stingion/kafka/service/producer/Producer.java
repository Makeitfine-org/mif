/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.kafka.service.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public abstract class Producer<Event> {

    protected String topic;

    @Autowired
    protected KafkaTemplate<String, Event> kafkaTemplate;

    public Producer(String topicName) {
        this.topic = topicName;
    }

    public void sendMessage(Event message) {
        this.kafkaTemplate.send(topic, message);
        log.info(String.format("#### -> Producing message -> %s", message));
    }
}
