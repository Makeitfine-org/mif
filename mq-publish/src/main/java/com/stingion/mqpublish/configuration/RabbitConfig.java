/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.mqpublish.configuration;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchange:#{null}}")
    private String exchange;

    @Value("${rabbitmq.queue:#{null}}")
    private String queue;

    @Bean
    public Queue secretUrlQueue() {
        return QueueBuilder.durable(queue).build();
    }

    @Bean
    public Exchange secretUrlExchange() {
        return ExchangeBuilder.directExchange(exchange).build();
    }

    @SuppressWarnings("missingjavadocmethod")
    @Bean
    public Binding binding(Queue secretUrlQueue, Exchange secretUrlExchange) {
        if (secretUrlExchange instanceof DirectExchange) {
            DirectExchange exchange = (DirectExchange) secretUrlExchange;
            return BindingBuilder.bind(secretUrlQueue).to(exchange).with("");
        } else {
            throw new ClassCastException("secretUrlExchange isn't instanceof" + DirectExchange.class.getName());
        }
    }

    @Bean
    public RabbitAdmin admin(ConnectionFactory cf) {
        return new RabbitAdmin(cf);
    }

    @SuppressFBWarnings(value = "SIC_INNER_SHOULD_BE_STATIC", justification = "Service listener for Rabbit Admin")
    @Component
    @RequiredArgsConstructor
    public class RabbitMqStartup implements ApplicationListener<ApplicationReadyEvent> {

        private final RabbitAdmin rabbitAdmin;

        @Override
        public void onApplicationEvent(final ApplicationReadyEvent event) {
            rabbitAdmin.initialize();
        }
    }
}
