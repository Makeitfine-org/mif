/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.mqconsume.configuration;

import com.stingion.util.mq.Message;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Subcriber {

    private final List<Message> queueMessages;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receivedMessage(Message msg) {
        log.info("Got message: \"{}\" from secretUrl", msg);
        queueMessages.add(msg);
    }
}
