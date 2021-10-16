/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.cache.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/cache")
@AllArgsConstructor
public class CacheServiceController {

    @GetMapping
    public ResponseEntity<String> hello() {
        log.info("Cache microservice (cache-service module)"); //todo: can be removed: checking conn
        return ResponseEntity.ok("Hello from \"cache-service\" module!");
    }
}
