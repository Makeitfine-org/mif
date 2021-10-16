/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserInfoControllerIT extends ControllerITProvision {

    @Test
    public void usersAndItsRoles() {
        String expected = "admin:{ADMIN}bill:{USER}danny:{USER}kenny:{ADMIN,DBA}nicole:{DBA}sam:{ADMIN}"
                + "sudo:{USER,ADMIN,DBA}tsuser:{USER}";

        String actual = getResponseBody("/userInfo/usersAndItsRoles");

        assertEquals(expected, actual);
    }
}
