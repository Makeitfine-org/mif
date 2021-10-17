/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.repository;

import static com.stingion.yaypay.data.repository.DatasourceConfig.MYSQL_DOCKER_IMAGE_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SuppressWarnings("CPD-START")
@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = AnyRepositoryTest.Initializer.class)
public class AnyRepositoryTest {

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>(MYSQL_DOCKER_IMAGE_NAME)
            .withDatabaseName("db")
            .withUsername("sa")
            .withPassword("sa");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + MY_SQL_CONTAINER.getJdbcUrl(),
                    "spring.datasource.password=" + MY_SQL_CONTAINER.getPassword(),
                    "spring.datasource.username=" + MY_SQL_CONTAINER.getUsername(),
                    "spring.datasource.driver-class-name=" + MY_SQL_CONTAINER.getDriverClassName(),
                    "spring.flyway.enabled=" + false
            );
            values.applyTo(configurableApplicationContext);
        }
    }

    @Test
    public void testRunning() {
        assertTrue(MY_SQL_CONTAINER.isRunning());
    }

    @Test
    public void test() {
        assertEquals(2, jdbcTemplate.queryForList("show databases").size());
        assertThat(jdbcTemplate.queryForList("show tables")).isEqualTo(Collections.EMPTY_LIST);
    }

    @Sql(value = "classpath:db/migration/main/V1_0__Init_DB.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "classpath:db/migration/after/V1_0__Drop_User_Table.sql",
            executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void testWithSqlAnnotation() {
        assertEquals(3, jdbcTemplate.queryForList("select * from USER").size());
        assertEquals(2, jdbcTemplate.queryForList("show databases").size());
        assertThat(jdbcTemplate.queryForList("show tables").size()).isEqualTo(1);
    }
}
