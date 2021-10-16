/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller.it;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.stingion.makeitfine.testconfiguration.CommonUtil;
import com.stingion.makeitfine.testconfiguration.IntegrationTest;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@IntegrationTest
abstract class ControllerITProvision {

    @Autowired
    protected TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Value("${protocolHost}")
    private String protocolHost;

    private String hostPort;

    @PostConstruct
    public void init() {
        hostPort = protocolHost + ":" + port;
    }

    protected String getResponseBody(String relativePath) {
        String responseBody = CommonUtil.getResponseBody(restTemplate, hostPort, relativePath, String.class);
        return responseBody != null ? responseBody : StringUtils.EMPTY;
    }

    protected String getHostPort() {
        return hostPort;
    }
}
