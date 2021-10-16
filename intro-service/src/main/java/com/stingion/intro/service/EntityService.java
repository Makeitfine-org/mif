/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.intro.service;

import java.util.List;

public interface EntityService<T> {

    List<T> findAll();

    T findById(String id);

    T insert(T entity);

    T save(T entity);

    void delete(T entity);
}
