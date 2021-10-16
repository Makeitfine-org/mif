/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model;

import java.util.List;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface EntityService<T> {

    List<T> findAll();

    @NonNull
    T findById(Integer id) throws NoSuchElementException;

    T save(T entity);

    T update(T entity);

    void delete(T entity);
}
