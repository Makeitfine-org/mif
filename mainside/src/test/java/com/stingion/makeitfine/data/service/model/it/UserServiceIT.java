/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.service.model.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// todo: add testing of user_profile many-to-many relation
public class UserServiceIT extends EntityServiceITAncestor<User> {

    @Autowired
    private UserService userService;

    @SuppressWarnings("argument.type.incompatible")
    @Test
    public void findBySSO() {
        User userExcepted = userService.findById(5);
        assertEquals(userExcepted, userService.findBySSO("kenny"));
        assertEquals(userExcepted, userService.findBySSO("KENNY"));
    }

    @SuppressWarnings("argument.type.incompatible")
    @Test
    public void findByEmail() {
        User userExcepted = userService.findById(3);
        assertEquals(userExcepted, userService.findByEmail("SAMY@XYZ.COM"));
        assertEquals(userExcepted, userService.findByEmail("samy@xyz.com"));
    }

    @Test
    public void isSsoIdOrEmailUserExists() {
        assertFalse(userService.isSsoIdOrEmailUserExists("anyUserSsoId"));
        assertFalse(userService.isSsoIdOrEmailUserExists("anyUserEmail@xxx.xxx"));
        assertTrue(userService.isSsoIdOrEmailUserExists("admin"));
        assertTrue(userService.isSsoIdOrEmailUserExists("kenny@xyz.com"));
    }
}
