/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.repository;

import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ActiveProfiles("test")
@SpringBootTest
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class},
        mergeMode = MERGE_WITH_DEFAULTS)
@FlywayTest
public class SampleTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @FlywayTest
    @Sql({"/db/migration/main/V1_0__Init_DB.sql", "/db/migration/other/V1_0__Insert_to_user_test_table.sql"})
    @Test
    public void testDefault() {
        Assertions.assertThat(3).isEqualTo(jdbcTemplate.queryForList("select * from USER_TEST").size());
        Assertions.assertThat("Something else").isEqualTo(
                jdbcTemplate.queryForObject("select name from USER_TEST where name='Something else'", String.class));
        Assertions.assertThat(3).isEqualTo(jdbcTemplate.queryForList("select * from USER").size());
    }

    @FlywayTest(invokeMigrateDB = false)
    @Test
    public void testNoMigration() {
        Assertions.assertThat(new ArrayList<>()).isEqualTo(jdbcTemplate.queryForList("show tables"));
    }

    @FlywayTest(locationsForMigrate = {"db/migration/main"}, overrideLocations = true)
    @Test
    public void changeMigrationLocation() {
        Assertions.assertThat(3).isEqualTo(jdbcTemplate.queryForList("select * from USER").size());
    }

    @FlywayTest(locationsForMigrate = {"db/migration/test2"}, overrideLocations = true, flywayName = "flyway")
    @Test
    public void changeMigrationLocation2() {
        Assertions.assertThat(0).isEqualTo(jdbcTemplate.queryForList("select * from CAR_TEST").size());
    }
}
