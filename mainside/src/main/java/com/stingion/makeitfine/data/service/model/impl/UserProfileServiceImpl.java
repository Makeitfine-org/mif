/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model.impl;

import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import com.stingion.makeitfine.data.service.model.UserProfileService;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl extends EntityServiceImpl<UserProfile>
        implements UserProfileService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserProfile> findByUserProfileType(UserProfileType profileType) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserProfile> criteria = builder.createQuery(UserProfile.class);
        Root<UserProfile> userProfileRoot = criteria.from(UserProfile.class);
        criteria.select(userProfileRoot);
        criteria.where(builder.equal(userProfileRoot.get("type"), profileType.getUserProfileType()));
        List<UserProfile> userProfiles = entityManager.createQuery(criteria).getResultList();
        return Optional.ofNullable(!userProfiles.isEmpty() ? userProfiles.get(0) : null);
    }
}
