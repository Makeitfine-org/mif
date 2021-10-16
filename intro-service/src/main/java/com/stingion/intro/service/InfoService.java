/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.intro.service;

import com.stingion.intro.domain.Info;

public interface InfoService extends EntityService<Info> {

    Info findByKey(String key);
}
