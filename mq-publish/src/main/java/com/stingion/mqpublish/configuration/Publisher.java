/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.mqpublish.configuration;

import com.stingion.util.mq.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Publisher {

    private final AmqpTemplate template;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Lookup
    @Qualifier("time")
    @Nullable
    public String time() {
        return null;
    }

    public void produceMsg(String msg) {
        log.info("Sent message \"{}\" to secretUrl", msg);
        template.convertAndSend(exchange, "", "[" + time() + "] " + msg);
    }

    public void produceMsg(Message message) {
        log.info("Sent message \"{}\" to secretUrl", message);
        template.convertAndSend(exchange, "", message);
    }
}
