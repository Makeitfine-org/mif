/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = BookServiceMysqlTestcontainersTest.Initializer.class)
public class BookServiceMysqlTestcontainersTest {

    @SuppressWarnings("CPD-START")
    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>()
            .withDatabaseName("jooq_db")
            .withUsername("sa")
            .withPassword("sa");

    @Autowired
    private BookService bookService;

    @Autowired
    private DSLContext context;

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + MY_SQL_CONTAINER.getJdbcUrl(),
                    "spring.datasource.password=" + MY_SQL_CONTAINER.getPassword(),
                    "spring.datasource.username=" + MY_SQL_CONTAINER.getUsername(),
                    "spring.datasource.driver-class-name=" + MY_SQL_CONTAINER.getDriverClassName(),
                    "spring.flyway.enabled=" + true
            );
            values.applyTo(configurableApplicationContext);
        }
    }

    @Test
    public void test() {
        assertEquals(2, bookService.getBooks().size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testJooq() {
        Object o = context.selectOne().fetch().get(0);
        assertNotNull(o);

        Field<?> field = DSL.field("title");

        Result<Record1<Object>> fetch = context.select((Field<Object>) field).from(DSL.table("book")).fetch();
        assertEquals(2, fetch.size());
    }
}
