/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.test;

import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import com.stingion.makeitfine.data.service.impl.EmailServiceImpl;
import com.stingion.makeitfine.data.service.impl.InfoServiceImpl;
import com.stingion.makeitfine.data.service.model.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class ServiceTestProvision {

    @InjectMocks
    @Spy
    protected InfoServiceImpl infoService;
    @InjectMocks
    @Spy
    protected EmailServiceImpl emailService;
    @Mock
    private UserService userService;

    @BeforeEach
    public void beforeEach() {
        U<User> u = User::new;

        User user1 = u.create();
        user1.setSsoId("user1");
        user1.setEmail("user1@any.xxxmail");

        User user2 = u.create();
        user2.setSsoId("user2");
        user2.setEmail("user2@any.xyz");

        User user3 = u.create();
        user3.setSsoId("user3");
        user3.setEmail("user3@any.xxxmail");

        UserProfile adminUp = new UserProfile();
        adminUp.setId(1);
        adminUp.setType(UserProfileType.ADMIN.getUserProfileType());

        UserProfile userUp = new UserProfile();
        userUp.setId(2);
        userUp.setType(UserProfileType.USER.getUserProfileType());

        UserProfile dbaUp = new UserProfile();
        dbaUp.setId(3);
        dbaUp.setType(UserProfileType.DBA.getUserProfileType());

        user1.setUserProfiles(Sets.newHashSet(adminUp, dbaUp, userUp));
        user3.setUserProfiles(Sets.newHashSet(adminUp, dbaUp, userUp));

        when(userService.findAll()).thenReturn(Lists.newArrayList(user1, user2, user3));
    }

    @FunctionalInterface
    private interface U<U extends User> {

        U create();
    }
}
