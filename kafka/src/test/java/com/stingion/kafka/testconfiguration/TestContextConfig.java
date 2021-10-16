/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.kafka.testconfiguration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ImportResource;

@TestConfiguration
@ImportResource("classpath:test-spring-context.xml")
public class TestContextConfig {
}
