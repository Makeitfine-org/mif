/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model.impl;

import com.stingion.makeitfine.data.repository.EntityRepository;
import com.stingion.makeitfine.data.service.model.EntityService;
import java.util.List;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EntityServiceImpl<T> implements EntityService<T> {

    @Autowired
    private EntityRepository<T> entityRepository;

    @Override
    public List<T> findAll() {
        return entityRepository.findAll();
    }

    @SuppressWarnings("method.invocation.invalid")
    @Override
    @NonNull
    public T findById(Integer id) throws NoSuchElementException {
        return entityRepository.findById(id).get();
    }

    @Override
    public T save(T entity) {
        return entityRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return save(entity);
    }

    @Override
    public void delete(T entity) {
        entityRepository.delete(entity);
    }
}
