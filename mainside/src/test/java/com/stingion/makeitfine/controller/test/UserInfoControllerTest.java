/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.stingion.makeitfine.controller.UserInfoController;
import com.stingion.makeitfine.data.service.InfoService;
import com.stingion.makeitfine.testconfiguration.UnitTest;
import java.util.ArrayList;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserInfoController.class)
@UnitTest
@Tag("controllerTest")
class UserInfoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private InfoService infoService;

    private ArrayList<String> usersAndItsRoles = Lists
            .newArrayList("user1:{USER,ADMIN,DBA}", "user2:{}", "user3:{USER,ADMIN,DBA}");

    @BeforeEach
    public void init() {
        when(infoService.usersAndItsRoles()).thenReturn(usersAndItsRoles);
    }

    @Test
    public void usersAndItsRoles() {
        webTestClient.get()
                .uri("/userInfo/usersAndItsRoles")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(CoreMatchers.equalTo(usersAndItsRoles.stream().reduce((e1, e2) -> e1 + "" + e2).orElseThrow()));

        verify(infoService, times(1)).usersAndItsRoles();
    }
}
