/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model.it;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.stingion.makeitfine.data.service.model.EntityService;
import com.stingion.makeitfine.testconfiguration.CommonUtil;
import com.stingion.makeitfine.testconfiguration.IntegrationTest;
import com.stingion.makeitfine.testconfiguration.ServiceTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@IntegrationTest
@Import(ServiceTestConfiguration.class)
@Transactional
public class EntityServiceITAncestor<T> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected EntityHelper<T> entityHelper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected EntityService<T> entityService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected ServiceTestConfiguration.EntityTestData<T> entityTestData;

    @Test
    public void findAllTest() {
        assertThat(entityTestData.getFindAll()).isEqualTo(entityService.findAll().toString());
    }

    // todo: move test to test from junit 5
    @Test
    public void findByIdTest() {
        assertThat(
                entityTestData.getFindById()
        ).isEqualTo(
                entityService.findById(entityTestData.getId()).toString()
        );
    }

    @DisplayName("Delete entity from storage")
    @Test
    public void deleteTest() {
        CommonUtil.deleteTest(entityHelper, entityService, entityTestData);
    }

    @DisplayName("Insert entity into storage")
    @Test
    public void saveTest() {
        int beforeSave = entityHelper.getCount();
        entityService.save(entityTestData.getSavedEntity());
        int afterSave = entityHelper.getCount();

        assertThat(beforeSave + 1 == afterSave);
        assertThat(entityHelper.isExist(entityTestData.getSavedEntity()));
    }

    @Test
    public void updateTest() {
        int beforeUpdate = entityHelper.getCount();

        T entityBeforeUpdate = entityService.findById(entityTestData.getId());
        assertThat(entityBeforeUpdate.toString())
                .isNotEqualTo(entityTestData.getUpdateEntity().toString());
        entityService.update(entityTestData.getUpdateEntity());

        int afterUpdate = entityHelper.getCount();

        T entityAfterUpdate = entityService.findById(entityTestData.getId());

        assertThat(entityTestData.getUpdateEntity().toString()).isEqualTo(entityAfterUpdate.toString());
        assertEquals(beforeUpdate, afterUpdate);
    }
}
