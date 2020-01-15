/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.mqpublish.configuration;

import com.stingion.util.mq.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  @Lookup
  @Qualifier("time")
  public String time() {
    return null;
  }

  @Value("${rabbitmq.exchange}")
  private String exchange;

  public void produceMsg(String msg) {
    log.info("Sent message \"{}\" to secretUrl", msg);
    template.convertAndSend(exchange, "", "[" + time() + "] " + msg);
  }

  public void produceMsg(Message message) {
    log.info("Sent message \"{}\" to secretUrl", message);
    template.convertAndSend(exchange, "", message);
  }
}