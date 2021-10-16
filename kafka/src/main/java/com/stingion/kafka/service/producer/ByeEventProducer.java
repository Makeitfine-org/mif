/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.kafka.service.producer;

import com.stingion.kafka.event.ByeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ByeEventProducer extends Producer<ByeEvent> {

    public ByeEventProducer(@Value("${spring.kafka.topic.bye}") String topicName) {
        super(topicName);
    }
}
