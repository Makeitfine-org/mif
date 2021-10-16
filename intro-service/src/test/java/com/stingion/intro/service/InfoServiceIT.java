/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.intro.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.stingion.intro.domain.Info;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("integration_test")
class InfoServiceIT {

    private static final Info INFO_1 = new Info("key1", "details1");
    private static final Info INFO_2 = new Info("key2", "details2");
    private static final Info INFO_3 = new Info("key3", "details3");

    @Autowired
    private InfoService infoService;

    @BeforeEach
    public void setup() {
        infoService.insert(INFO_1);
        infoService.insert(INFO_2);
        infoService.insert(INFO_3);
    }

    @AfterEach
    public void dispose() {
        infoService.findAll().forEach(i -> infoService.delete(i));
    }

    @Test
    public void findAll() {
        List<Info> findAll = infoService.findAll();

        assertThat(findAll.size()).isEqualTo(3);
        assertThat(findAll).contains(INFO_1);
        assertThat(findAll).contains(INFO_2);
        assertThat(findAll).contains(INFO_3);
    }

    @Test
    public void findByKey() {
        assertThat(infoService.findByKey("key2").getDetails()).isEqualTo("details2");
    }

    @Test
    public void findByNotExistingKey() {
        Throwable exception =
                assertThrows(NoSuchElementException.class, () -> infoService.findByKey("key4"));
        assertTrue(getMessage(exception).equalsIgnoreCase("No value present"));
    }

    @Test
    public void insert() {
        Info insertedInfo = new Info("insertedKey", "insertedDetails");
        infoService.insert(insertedInfo);
        List<Info> findAll = infoService.findAll();

        assertThat(findAll.size()).isEqualTo(4);
        assertThat(findAll).contains(insertedInfo);
    }

    @Test
    public void insertWithExistingKey() {
        assertThrows(DuplicateKeyException.class, () -> infoService.insert(new Info("key2", "any")));
    }

    @Test
    public void delete() {
        infoService.delete(INFO_3);
        List<Info> findAll = infoService.findAll();

        assertThat(findAll.size()).isEqualTo(2);
        assertThat(findAll).doesNotContain(INFO_3);
    }

    @Test
    public void deleteByNotExistingKey() {
        Throwable exception = assertThrows(IllegalStateException.class, () ->
                infoService.delete(new Info("any", "any")));
        assertTrue(getMessage(exception).startsWith("Could not obtain identifier"));
    }

    @Test
    public void findById() {
        Info foundByKey = infoService.findByKey("key2");
        Info foundByIdInfo = infoService.findById(foundByKey.getId());

        assertThat(foundByIdInfo).isEqualTo(foundByKey);
        assertThat(foundByIdInfo.getId()).isEqualTo(foundByKey.getId());
    }

    @Test
    public void findByNotExistingId() {
        Throwable exception = assertThrows(NoSuchElementException.class, () ->
                infoService.findById(UUID.randomUUID().toString() + "_unique"));
        assertTrue(getMessage(exception).equalsIgnoreCase("No value present"));
    }

    @Test
    public void save() {
        Info updateDetailsInfo = infoService.findByKey("key1");
        updateDetailsInfo.setDetails("details1New");
        infoService.save(updateDetailsInfo);
        assertThat(infoService.findByKey("key1").getDetails()).isEqualTo("details1New");

        Info savedInfo = new Info("anyKey", "anyDetails");
        infoService.insert(savedInfo);
        assertThat(infoService.findByKey("anyKey")).isEqualTo(savedInfo);

        assertThat(infoService.findAll().size()).isEqualTo(4);
    }

    private String getMessage(Throwable exception) {
        String message = exception.getMessage();
        return message != null ? message : StringUtils.EMPTY;
    }
}
