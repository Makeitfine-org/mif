/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.mqpublish.web.controller;

import com.stingion.mqpublish.configuration.Publisher;
import com.stingion.util.mq.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mqpublish")
@RequiredArgsConstructor
public class MqPublishController {

    private final Publisher publisher;

    @GetMapping
    public ResponseEntity<String> hello() {
        log.info("Publisher microservice (mq-publish module)"); //todo: can be removed: checking conn
        return ResponseEntity.ok("Hello from \"mq-publish\" module!");
    }

    /**
     * SecretUrl access.
     *
     * @param msg       message to send
     * @param serialize true is default if skip in request
     * @return response
     */
    @GetMapping("/secretUrl")
    public String secretUrl(@RequestParam("msg") String msg,
            @RequestParam(value = "serialize", required = false) Boolean serialize) {
        serialize = serialize == null || serialize;
        if (serialize) {
            publisher.produceMsg(new Message(msg));
        } else {
            publisher.produceMsg(msg);
        }
        return "Access to secret Url and send message (serialize = " + serialize + "): " + msg;
    }
}
