/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.intro.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring-context.xml")
@RequiredArgsConstructor
public class ContextConfig {
}
