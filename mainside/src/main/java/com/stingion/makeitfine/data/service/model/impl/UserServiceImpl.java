/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model.impl;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.service.model.UserService;
import com.stingion.makeitfine.data.service.util.ServiceHelper;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends EntityServiceImpl<User> implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    @Nullable
    public User findBySSO(String ssoId) {
        return ServiceHelper.findEntityByItsAttribute(entityManager, "ssoId", ssoId, User.class);
    }

    @Transactional
    @Override
    @Nullable
    public User findByEmail(String email) {
        return ServiceHelper.findEntityByItsAttribute(entityManager, "email", email, User.class);
    }

    @Transactional
    @Override
    public boolean isSsoIdOrEmailUserExists(String ssoIdOrEmail) {
        return Optional.ofNullable(userService.findBySSO(ssoIdOrEmail))
                .or(() -> Optional.ofNullable(userService.findByEmail(ssoIdOrEmail)))
                .map(user -> true)
                .orElse(false);
    }
}
