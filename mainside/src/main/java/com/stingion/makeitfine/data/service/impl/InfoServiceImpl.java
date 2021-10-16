/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.service.InfoService;
import com.stingion.makeitfine.data.service.model.UserService;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private UserService userService;

    @Override
    public List<String> usersAndItsRoles() {
        return userService.findAll().stream()
                .sorted(Comparator.comparing(User::getSsoId))
                .map(user -> user.getSsoId().toLowerCase(Locale.getDefault())
                        + ":{"
                        + Optional.ofNullable(user.getUserProfiles())
                        .flatMap(userProfiles -> userProfiles.stream().map(u -> u.getType())
                                .reduce((t1, t2) -> t1 + "," + t2)).orElse("")
                        + "}")
                .collect(Collectors.toList());
    }
}
