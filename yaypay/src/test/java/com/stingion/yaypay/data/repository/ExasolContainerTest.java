/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.repository;

import static com.stingion.yaypay.data.repository.DatasourceConfig.MYSQL_DOCKER_IMAGE_NAME;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.exasol.containers.ExasolContainer;
import com.exasol.containers.ExasolContainerConstants;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Disabled
@SuppressWarnings("all")
@SuppressFBWarnings("BC_UNCONFIRMED_CAST_OF_RETURN_VALUE")
@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
//@TestExecutionListeners(value = FlywayTestExecutionListener.class, mergeMode = MERGE_WITH_DEFAULTS)
//@FlywayTest(invokeMigrateDB = false, invokeCleanDB = false)
@ContextConfiguration(initializers = ExasolContainerTest.Initializer.class)
public class ExasolContainerTest {

    @Container
    private static final ExasolContainer<? extends ExasolContainer<?>> EXASOL_CONTAINER =
            new ExasolContainer<>(ExasolContainerConstants.EXASOL_DOCKER_IMAGE_REFERENCE)
                    .withUsername("sys")
                    .withPassword("exasol")
                    .withLogConsumer(new Slf4jLogConsumer(log));

    @Container
    private static final MySQLContainer<?> MYSQL_CONTAINER = new MySQLContainer<>(MYSQL_DOCKER_IMAGE_NAME)
            .withDatabaseName("db")
            .withUsername("sa")
            .withPassword("sa");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    //                    "exasol.datasource.url=" + EXASOL_CONTAINER.getJdbcUrl(),
                    //                    "exasol.datasource.password=" + EXASOL_CONTAINER.getPassword(),
                    //                    "exasol.datasource.username=" + EXASOL_CONTAINER.getUsername(),
                    //                    "exasol.datasource.driver-class-name=" + EXASOL_CONTAINER.getDriverClassName()
                    "spring.datasource.url=" + MYSQL_CONTAINER.getJdbcUrl(),
                    "spring.datasource.password=" + MYSQL_CONTAINER.getPassword(),
                    "spring.datasource.username=" + MYSQL_CONTAINER.getUsername(),
                    "spring.datasource.driver-class-name=" + MYSQL_CONTAINER.getDriverClassName(),
                    "spring.flyway.enabled=" + false
            );
            values.applyTo(configurableApplicationContext);
        }
    }

    @Test
    void test() {
        assertTrue(jdbcTemplate.queryForList("show databases")
                .stream().filter(e -> e.get("Database").equals("yaypay_test")).findAny().isPresent());
    }
}
