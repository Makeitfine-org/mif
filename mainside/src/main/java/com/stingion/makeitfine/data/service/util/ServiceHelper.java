/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.util;

import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class ServiceHelper {

    private ServiceHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get single entity by any of its attribute.
     * If any of the arguments is {@code null}, then method {@code return null;}
     *
     * @param entityManager provided jpa entity manager
     * @param attr          entity attribute
     * @param attrValue     entity attribute value
     * @param entityClass   entity class
     * @param <T>           any model class.
     * @return single value or null
     */
    public static @Nullable <T> T findEntityByItsAttribute(@Nullable EntityManager entityManager, @Nullable String attr,
            @Nullable String attrValue, @Nullable Class<T> entityClass) {
        if (entityManager == null || attr == null || attrValue == null || entityClass == null) {
            return null;
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> r = cq.from(entityClass);
        cq.select(r);
        cq.where(cb.equal(r.get(attr), attrValue.toLowerCase(Locale.getDefault())));
        List<T> entities = entityManager.createQuery(cq).getResultList();

        return entities.size() > 0 ? entities.get(0) : null;
    }
}
