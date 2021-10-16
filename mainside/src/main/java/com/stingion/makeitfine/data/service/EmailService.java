/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service;

import java.util.List;
import java.util.Map;

public interface EmailService {

    /**
     * Check whether email belongs to any active admin.
     * @param email given email address
     * @return check result
     */
    boolean isActiveAdminEmail(String email);

    List<String> sortedUsersEmails();

    /**
     * Get map of {@code ssoId -> email} of users.
     * @return ssoId:email map
     */
    Map<String, String> ssoIdEmails();
}
