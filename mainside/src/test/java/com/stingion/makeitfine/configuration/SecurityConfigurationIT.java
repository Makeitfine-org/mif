/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.configuration;

import static com.stingion.makeitfine.testconfiguration.TestConstants.SECURE_RANDOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.State;
import com.stingion.makeitfine.testconfiguration.CommonUtil;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@SuppressWarnings("ConfigurationProperties")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles({"security_on_integration_test"})
@TestPropertySource("classpath:values-test.yml")
@ConfigurationProperties(prefix = "test.integration", ignoreInvalidFields = true)
class SecurityConfigurationIT {

    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Value("${protocolHost}")
    private String protocolHost;

    @Value("${basicAuthUser.username}")
    private String username;

    @Value("${basicAuthUser.password}")
    private String password;

    private String hostPort;

    @Autowired
    private ObjectMapper mapper;

    @PostConstruct
    public void init() {
        hostPort = protocolHost + ":" + port;
        restTemplate = restTemplate.withBasicAuth(username, password);
    }

    @BeforeAll
    public void beforeAll() {
        mapper.setAnnotationIntrospector(new IgnoreJacksonWriteOnlyAccess());
    }

    @SuppressWarnings("argument.type.incompatible")
    @AfterAll
    public void afterAll() {
        mapper.setAnnotationIntrospector(null);
    }

    @Test
    public void indexPageTest() {
        CommonUtil.indexPageTest(restTemplate, hostPort);
    }

    @Test
    public void getUserWithFirstId() {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setSsoId("bill");
        expectedUser.setEmail("bill@xyz.com");
        expectedUser.setState(State.ACTIVE);

        User actualUser = getResponseBody("/user/1", User.class);

        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getSsoId(), actualUser.getSsoId());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getState(), actualUser.getState());
    }

    @Test
    public void insertUserWithRandomSsoId() {

        User insertedUser = new User();
        insertedUser.setSsoId(UUID.randomUUID().toString().substring(0, 25));
        insertedUser.setPassword(UUID.randomUUID().toString());
        insertedUser.setEmail(String.format("any%s%s", SECURE_RANDOM.nextInt(), "@xxx.xxx"));
        insertedUser.setState(State.ACTIVE);
        insertedUser.setUserProfiles(Sets.newHashSet());

        int numberOfUsersBeforeInsert = getResponseBody("/user", List.class).size();
        var insertUserResponse = restTemplate.postForEntity("/user", insertedUser, Void.class);
        int numberOfUsersAfterInsert = getResponseBody("/user", List.class).size();
        assertEquals(numberOfUsersBeforeInsert, numberOfUsersAfterInsert - 1);

        String createdUserId = Optional.ofNullable(insertUserResponse.getHeaders().get("createdUserId"))
                .map(h -> h.get(0)).orElse(null);
        restTemplate.delete(String.format("/user/%s", createdUserId));
        int numberOfUsersAfterDelete = getResponseBody("/user", List.class).size();
        assertEquals(numberOfUsersAfterInsert, numberOfUsersAfterDelete + 1);
    }

    @SafeVarargs
    private <T> T getResponseBody(String relativePath, Class<T>... clasz) throws NoSuchElementException {
        @SuppressWarnings({"unchecked", "varargs"})
        Class<T>[] getRidOfWarningsClass = clasz;
        T responseBody = CommonUtil.getResponseBody(restTemplate, hostPort, relativePath, getRidOfWarningsClass);
        if (responseBody != null) {
            return responseBody;
        }
        throw new NoSuchElementException(String.format("hostPort: %s, relativePath: %s", hostPort, relativePath));
    }
}
