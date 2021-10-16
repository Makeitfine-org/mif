/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.repository;

import com.stingion.makeitfine.data.model.user.Contact;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends EntityRepository<Contact> {
}
