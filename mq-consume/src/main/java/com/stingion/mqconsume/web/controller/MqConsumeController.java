/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.mqconsume.web.controller;

import com.stingion.util.mq.Message;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mqconsume")
@AllArgsConstructor
public class MqConsumeController {

    private final List<Message> queueMessages;

    @GetMapping
    public ResponseEntity<String> hello() {
        log.info("Consumer microservice (mq-consume module)"); //todo: can be removed: checking conn
        return ResponseEntity.ok("Hello from \"mq-consume\" module!");
    }

    @GetMapping("/queue")
    public List<Message> queueMessages() {
        return queueMessages;
    }
}
