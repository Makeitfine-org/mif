/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.makeitfine.data.graphql.service;

import com.stingion.makeitfine.data.repository.EntityRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GQEntityServiceImpl<E> {

    @Autowired
    private EntityRepository<E> entityRepository;

    public List<E> getEntities(int count) {
        return entityRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    public Optional<E> getEntity(int id) {
        return entityRepository.findById(id);
    }
}
