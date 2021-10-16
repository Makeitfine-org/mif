/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.intro.service.impl;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import com.stingion.intro.domain.Info;
import com.stingion.intro.service.InfoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl extends EntityServiceImpl<Info> implements InfoService {

    /**
     * Find Info by key.
     *
     * @param key key for search by
     * @return Info
     */
    @SuppressWarnings("method.invocation.invalid")
    public Info findByKey(String key) {
        var info = new Info();
        info.setKey(key);

        var matcher = ExampleMatcher.matching()
                .withMatcher("key", exact())
                .withIgnorePaths("details");

        var example = Example.of(info, matcher);

        return entityRepository.findOne(example).get();
    }
}
