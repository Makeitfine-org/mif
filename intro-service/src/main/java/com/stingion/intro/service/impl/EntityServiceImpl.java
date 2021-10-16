/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.intro.service.impl;

import com.stingion.intro.repository.EntityRepository;
import com.stingion.intro.service.EntityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class EntityServiceImpl<T> implements EntityService<T> {

    @Autowired(required = false)
    protected EntityRepository<T> entityRepository;

    @Override
    public List<T> findAll() {
        return entityRepository.findAll();
    }

    @SuppressWarnings("method.invocation.invalid")
    @Override
    public T findById(String id) {
        return entityRepository.findById(id).get();
    }

    @Override
    public T insert(T entity) {
        return entityRepository.insert(entity);
    }

    @Override
    public T save(T entity) {
        return entityRepository.save(entity);
    }

    @Override
    public void delete(T entity) {
        entityRepository.delete(entity);
    }
}
