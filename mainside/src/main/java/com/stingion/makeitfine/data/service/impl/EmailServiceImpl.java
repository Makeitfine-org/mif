/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.impl;

import com.google.common.collect.Sets;
import com.stingion.makeitfine.data.model.utils.State;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import com.stingion.makeitfine.data.service.EmailService;
import com.stingion.makeitfine.data.service.UserService;
import java.util.Optional;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  @Autowired
  private UserService userService;

  private EmailValidator emailValidator = EmailValidator.getInstance(true, true);

  @Override
  public boolean isActiveAdminEmail(String email) {
    return !emailValidator.isValid(email) ? false :
        Optional.ofNullable(userService.findByEmail(email.toLowerCase()))
            .flatMap(user ->
                Optional.ofNullable(user.getState() == State.ACTIVE &&
                    user.getUserProfiles().stream()
                        .anyMatch(userProfile ->
                            Sets.newHashSet(
                                UserProfileType.ADMIN.getUserProfileType(),
                                UserProfileType.DBA.getUserProfileType())
                                .contains(userProfile.getType())
                        )
                )
            )
            .orElse(false);
  }
}
