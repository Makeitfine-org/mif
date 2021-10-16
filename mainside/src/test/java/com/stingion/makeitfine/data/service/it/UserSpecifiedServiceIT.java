/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.State;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import com.stingion.makeitfine.data.service.UserSpecifiedService;
import com.stingion.makeitfine.data.service.model.UserService;
import com.stingion.makeitfine.testconfiguration.IntegrationTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@IntegrationTest
@Transactional
class UserSpecifiedServiceIT {

    @Autowired
    private UserSpecifiedService userSpecifiedService;

    @Autowired
    private UserService userService;

    @Test
    public void specifiedMailServiceUsers() {
        String emailServerName = "xyz.com";

        List<User> usersExpected = Lists.newArrayList(
                userService.findById(1),
                userService.findById(2),
                userService.findById(3),
                userService.findById(4),
                userService.findById(5)
        );

        List<User> usersActual = userSpecifiedService.specifiedMailServiceUsers(emailServerName);
        assertEquals(usersExpected, usersActual);
    }

    @Test
    public void roleUsers() {
        UserProfileType userProfileType = UserProfileType.DBA;

        List<User> usersExpected = Lists.newArrayList(
                userService.findById(4),
                userService.findById(5),
                userService.findById(6)
        );

        List<User> usersActual = userSpecifiedService.roleUsers(userProfileType);
        assertEquals(usersExpected, usersActual);
    }

    @Test
    public void stateUsers() {
        State userState = State.ACTIVE;

        List<User> usersExpected = Lists.newArrayList(
                userService.findById(1),
                userService.findById(2),
                userService.findById(6),
                userService.findById(7),
                userService.findById(8)
        );

        List<User> usersActual = userSpecifiedService.stateUsers(userState);
        assertEquals(usersExpected, usersActual);
    }
}
