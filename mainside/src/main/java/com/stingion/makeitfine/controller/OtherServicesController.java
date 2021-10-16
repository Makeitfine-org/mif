/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/microservice")
@RequiredArgsConstructor
@Slf4j
public class OtherServicesController {

    private final RestTemplate restTemplate;

    @Value("${intro-service.base-url:#{null}}")
    private String introServiceBaseUrl;

    @Value("${mq-publish.base-url:#{null}}")
    private String mqPublishBaseUrl;

    @Value("${mq-consume.base-url:#{null}}")
    private String mqConsumeBaseUrl;

    @Value("${cache-service.base-url:#{null}}")
    private String cacheServiceBaseUrl;

    @Value("${kafka.base-url:#{null}}")
    private String kafkaBaseUrl;

    /**
     * Get response from {@code intro-service} module.
     *
     * @return response
     */
    @SuppressWarnings("CPD-START")
    @GetMapping(path = "intro/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> introMS() {
        return getResponseEntity(introServiceBaseUrl, "intro");
    }

    /**
     * Get response from {@code mq-publish} module.
     *
     * @return response
     */
    @GetMapping(path = "mqpublish/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mqPublishMS() {
        return getResponseEntity(mqPublishBaseUrl, "mqpublish");
    }

    /**
     * Get response from {@code mq-consume} module.
     *
     * @return response
     */
    @GetMapping(path = "mqconsume/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mqConsumeMS() {
        return getResponseEntity(mqConsumeBaseUrl, "mqconsume");
    }

    /**
     * Get response from {@code cache-service} module.
     *
     * @return response
     */
    @GetMapping(path = "cache/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cacheServiceMS() {
        return getResponseEntity(cacheServiceBaseUrl, "cache");
    }

    /**
     * Get response from {@code kafka} module.
     *
     * @return response
     */
    @SuppressWarnings("CPD-END")
    @GetMapping(path = "kafka/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> kafkaMS() {
        return getResponseEntity(kafkaBaseUrl, "kafka");
    }

    /**
     * Get author of project.
     *
     * @return response
     */
    @GetMapping(path = "intro/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> author() {
        return getResponseEntity(introServiceBaseUrl, "info/details/author");
    }

    private @NonNull ResponseEntity<String> getResponseEntity(String serviceBaseUrl, String path) {
        String url = UriComponentsBuilder.fromHttpUrl(serviceBaseUrl)
                .path(path)
                .toUriString();
        log.info("request to '{}'", url);
        return ResponseEntity.ok(getResponseBodyOrEmpty(url));
    }

    private @NonNull String getResponseBodyOrEmpty(String url) {
        String body = restTemplate.getForEntity(url, String.class).getBody();
        if (body != null) {
            return body;
        }
        return StringUtils.EMPTY;
    }
}
