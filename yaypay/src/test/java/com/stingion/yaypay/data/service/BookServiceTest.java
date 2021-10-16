/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit5.annotation.FlywayTestExtension;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ActiveProfiles("test")
@SpringBootTest
@FlywayTestExtension
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
@FlywayTest
public class BookServiceTest {

    @Autowired
    private DSLContext context;

    @SuppressWarnings("unchecked")
    @Test
    public void testJooq() {
        Object o = context.selectOne().fetch().get(0);
        assertNotNull(o);

        Field<?> field = DSL.field("title");

        Result<Record1<Object>> fetch = context.select((Field<Object>) field).from(DSL.table("book")).fetch();
        assertEquals(2, fetch.size());

        //Working for mysql
        //        Result<Record2<Object, Object>> fetchJoin = context.select(
        //                DSL.field(DSL.name("book", "title")),
        //                DSL.field(DSL.name("author", "name")))
        //                .from(DSL.table("book"))
        //                .innerJoin(DSL.table("author"))
        //                .on(DSL.field(DSL.name("book", "id")).eq(DSL.field(DSL.name("author", "id"))))
        //                .fetch();
        //
        //        assertEquals(2, fetchJoin.size());
    }
}
