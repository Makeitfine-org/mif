/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class EmailServiceTest extends ServiceTestProvision {

    @Test
    public void ssoIdEmails() {
        Map<String, String> expected = new HashMap<>();
        expected.put("user1", "user1@any.xxxmail");
        expected.put("user2", "user2@any.xyz");
        expected.put("user3", "user3@any.xxxmail");

        Map<String, String> actual = emailService.ssoIdEmails();
        assertEquals(expected, actual);
    }
}
