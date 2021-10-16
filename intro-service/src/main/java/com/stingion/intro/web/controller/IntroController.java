/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.intro.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/intro")
public class IntroController {

    @GetMapping
    public ResponseEntity<String> hello() {
        log.info("Intro microservice (intro-service module)"); //todo: can be removed: checking connection
        return ResponseEntity.ok("Hi!");
    }
}
