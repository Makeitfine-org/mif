/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.kafka.web.controller;

import com.stingion.kafka.event.ByeEvent;
import com.stingion.kafka.event.HelloEvent;
import com.stingion.kafka.event.MessageEvent;
import com.stingion.kafka.service.producer.ByeEventProducer;
import com.stingion.kafka.service.producer.HelloEventProducer;
import com.stingion.kafka.service.producer.MessageEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final HelloEventProducer helloEventProducer;
    private final ByeEventProducer byeEventProducer;
    private final MessageEventProducer messageEventProducer;

    @GetMapping
    public ResponseEntity<String> hello() {
        log.info("Kafka microservice (kafka module)"); //todo: can be removed: checking conn
        return ResponseEntity.ok("Hello from \"kafka\" module!");
    }

    @PostMapping(value = "/publish", params = "hello")
    public void sendHelloMessage(@RequestParam("hello") String helloMessage) {
        helloEventProducer.sendMessage(new HelloEvent("hello >> " + helloMessage));
    }

    @PostMapping(value = "/publish", params = "bye")
    public void sendByeMessage(@RequestParam("bye") String byeMessage) {
        byeEventProducer.sendMessage(new ByeEvent("bye >> " + byeMessage));
    }

    @PostMapping(value = "/publish", params = "message")
    public void sendMessage(@RequestParam("message") String message) {
        messageEventProducer.sendMessage(new MessageEvent(message));
    }

    @PostMapping(value = "/publish0", params = "message")
    public void sendMessagePartition0(@RequestParam("message") String message) {
        messageEventProducer.sendMessagePartition0(new MessageEvent(message));
    }

    @PostMapping(value = "/publish1", params = "message")
    public void sendMessagePartition1(@RequestParam("message") String message) {
        messageEventProducer.sendMessagePartition1(new MessageEvent(message));
    }
}
