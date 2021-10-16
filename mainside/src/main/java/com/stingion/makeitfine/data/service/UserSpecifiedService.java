/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.State;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import java.util.List;

public interface UserSpecifiedService {

    /**
     * Get list of users of specified email host (e.g gmail.com, yahoo.com, ukr.net).
     *
     * @param emailHost - email host (e.g. gmail.com)
     * @return users
     */
    List<User> specifiedMailServiceUsers(String emailHost);

    /**
     * Get list of users of specified type.
     *
     * @param userProfileType profile type
     * @return users
     */
    List<User> roleUsers(UserProfileType userProfileType);

    /**
     * Get list of users of specified state.
     *
     * @param userState state
     * @return users
     */
    List<User> stateUsers(State userState);
}
