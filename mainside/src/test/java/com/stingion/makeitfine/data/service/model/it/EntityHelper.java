/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model.it;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;

@Slf4j
public class EntityHelper<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Setter(value = AccessLevel.PROTECTED)
    private Class<T> entityClass;

    public int getCount() {
        return ((Long)
                entityManager
                        .createQuery("select count(*) from " + getEntityClass().getSimpleName())
                        .getSingleResult())
                .intValue();
    }

    @SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
    public boolean isExist(@NonNull T entity) {
        try {
            Method getIdMethod = entity.getClass().getMethod("getId");
            Object getIdIMethodInvokeResult = getIdMethod.invoke(entity);
            if (getIdIMethodInvokeResult == null) {
                return false;
            }
            T dbEntity = getEntityById((Integer) getIdIMethodInvokeResult);
            return entity.toString().equals(dbEntity != null ? dbEntity.toString() : StringUtils.EMPTY);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.debug(Objects.toString(e.getMessage(), ""), e);
            return false;
        }
    }

    private T getEntityById(int id) {
        // Can't avoid the expression without suppression in normal coding way (un-normal way is play with optionals)
        @SuppressWarnings("unchecked")
        T dbEntity = (T) entityManager
                .createQuery("from " + getEntityClass().getSimpleName() + " e where e.id = :inputId")
                .setParameter("inputId", id).getResultList().stream()
                .findAny()
                .orElse(null);
        return dbEntity;
    }

    @SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
    protected Class<T> getEntityClass() {
        if (entityClass == null) {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            // Can't avoid the expression without suppression in normal coding way
            @SuppressWarnings({"unchecked", "dereference.of.nullable"})
            Class<T> actualTypeArgument = (Class<T>) parameterizedType.getActualTypeArguments()[0];
            this.entityClass = actualTypeArgument;
        }
        return entityClass;
    }
}
