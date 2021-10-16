/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.State;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import com.stingion.makeitfine.data.service.UserSpecifiedService;
import com.stingion.makeitfine.data.service.model.UserService;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSpecifiedServiceImpl implements UserSpecifiedService {

    @Autowired
    private UserService userService;

    @Override
    public List<User> specifiedMailServiceUsers(String emailHost) {
        return filteredUsers(user -> user
                .getEmail().toLowerCase(Locale.getDefault())
                .endsWith(emailHost.toLowerCase(Locale.getDefault())));
    }

    @Override
    public List<User> roleUsers(UserProfileType userProfileType) {
        return filteredUsers(user -> user
                .getUserProfiles().stream()
                .anyMatch(up -> up.getType().equals(userProfileType.getUserProfileType()))
        );
    }

    @Override
    public List<User> stateUsers(State userState) {
        return filteredUsers(user -> user.getState() == userState);
    }

    private List<User> filteredUsers(Predicate<User> userFilter) {
        return userService.findAll().stream().filter(userFilter).collect(Collectors.toList());
    }
}
