/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model;

import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import java.util.Optional;

public interface UserProfileService extends EntityService<UserProfile> {

    Optional<UserProfile> findByUserProfileType(UserProfileType profileType);
}
