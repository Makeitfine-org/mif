/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import com.stingion.makeitfine.data.service.EmailService;
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
class EmailServiceIT {

    @Autowired
    private EmailService emailService;

    @Test
    public void isActiveAdminEmail() {
        assertFalse(emailService.isActiveAdminEmail("some word"));
        assertFalse(emailService.isActiveAdminEmail("any_email@xyz.com"));
        assertFalse(emailService.isActiveAdminEmail("bill@xyz.com"));
        assertFalse(emailService.isActiveAdminEmail("BILL@XYZ.COM"));
        assertFalse(emailService.isActiveAdminEmail("samy@xyz.com"));
        assertFalse(emailService.isActiveAdminEmail("SAMY@XYZ.COM"));
        assertFalse(emailService.isActiveAdminEmail("kenny@xyz.com"));
        assertFalse(emailService.isActiveAdminEmail("nicloe@xyz.com"));

        assertTrue(emailService.isActiveAdminEmail("admin@xxx.xxx"));
        assertTrue(emailService.isActiveAdminEmail("ADMIN@xxx.xxx"));
    }

    @Test
    public void sortedUsersEmails() {
        List<String> expected = Lists.newArrayList(
                "admin@xxx.xxx",
                "bill@xyz.com",
                "danny@xyz.com",
                "kenny@xyz.com",
                "nicloe@xyz.com",
                "samy@xyz.com",
                "sudo@xxx.xxx",
                "tsuser@xxx.xxx"
        );
        List<String> actual = emailService.sortedUsersEmails();

        assertEquals(expected, actual);
    }
}
