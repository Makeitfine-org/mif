/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.mqpublish.configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;

@Configuration
@ImportResource("classpath:spring-context.xml")
@RequiredArgsConstructor
public class ContextConfig {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public String time() {
        return DATE_TIME_FORMATTER.format(LocalDateTime.now());
    }
}
