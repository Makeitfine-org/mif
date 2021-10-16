/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.jupiter.api.Test;

public class InfoServiceTest extends ServiceTestProvision {

    @Test
    public void usersAndItsRoles() {
        List<String> expected = Lists
                .newArrayList("user1:{USER,ADMIN,DBA}", "user2:{}", "user3:{USER,ADMIN,DBA}");
        List<String> actual = infoService.usersAndItsRoles();
        assertEquals(expected, actual);
    }
}
