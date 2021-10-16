/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.State;
import com.stingion.makeitfine.data.service.model.UserService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        User user = userService.findBySSO(ssoId);
        if (user == null) {
            String userNotFound = "User not found";
            LOG.warn("{}, ssoID = {}", userNotFound, ssoId);
            throw new UsernameNotFoundException(userNotFound);
        }
        if (user.getState() != State.ACTIVE) {
            String userIsNotActive = "User is " + user.getState();
            LOG.warn("{}, user = {}", userIsNotActive, user);
            throw new UsernameNotFoundException(userIsNotActive);
        }
        LOG.info("Logged user = {}", user);

        return new org.springframework.security.core.userdetails.User(
                user.getSsoId(),
                user.getPassword(),
                user.getState() == State.ACTIVE,
                true,
                true,
                true,
                getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserProfile userProfile : user.getUserProfiles()) {
            LOG.info("UserProfile : " + userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        }
        LOG.info("authorities :" + authorities);
        return authorities;
    }
}
