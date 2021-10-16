/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.cache.configuration;

import com.stingion.util.mq.Message;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

@Configuration
@ImportResource("classpath:spring-context.xml")
@RequiredArgsConstructor
public class ContextConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public List<Message> queueMessages() {
        return new LinkedList<>();
    }
}
