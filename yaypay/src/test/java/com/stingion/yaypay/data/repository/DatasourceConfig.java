/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 */

package com.stingion.yaypay.data.repository;

/*
 * Author : Pavlo Omelianchuk
 * Date Created: 2020/08/26
 */

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SuppressWarnings({"all", "CPD-START"})
@SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
@EnableTransactionManagement
@TestConfiguration
public class DatasourceConfig {

    public static final String MYSQL_DOCKER_IMAGE_NAME = "mysql:8.0.26";

    @Primary
    @Bean(name = "mysqlDatasource")
    public DataSource mysqlDataSource(@Value("${spring.datasource.driver-class-name}") String driverClassName,
            @Value("${spring.datasource.url}") String jdbcUrl,
            @Value("${spring.datasource.username}") String userName,
            @Value("${spring.datasource.password}") String password) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);

        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setMinimumIdle(10);
        hikariConfig.setIdleTimeout(30);
        hikariConfig.setValidationTimeout(30000);
        hikariConfig.setConnectionTestQuery("SELECT 1");

        return new HikariDataSource(hikariConfig);
    }

    @Primary
    @Bean("mysqlEntityManagerFactory")
    public EntityManagerFactory mysqlEntityManagerFactory(
            @Qualifier("mysqlDatasource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaVendorAdapter(getJpaHibernateVendorAdapter());
        em.setPersistenceUnitName("other");
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Primary
    @Bean
    public HibernateJpaVendorAdapter getJpaHibernateVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return adapter;
    }

    @Primary
    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("mysqlEntityManagerFactory")
            EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean(name = "exasolDatasource")
    public DataSource exasolDataSource(@Value("${exasol.datasource.driver-class-name}") String driverClassName,
            @Value("${exasol.datasource.url}") String jdbcUrl,
            @Value("${exasol.datasource.username}") String userName,
            @Value("${exasol.datasource.password}") String password) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);

        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setMinimumIdle(10);
        hikariConfig.setIdleTimeout(30);
        hikariConfig.setValidationTimeout(30000);
        hikariConfig.setConnectionTestQuery("SELECT 1");

        return new HikariDataSource(hikariConfig);
    }

    @Bean("exasolEntityManagerFactory")
    public EntityManagerFactory exasolEntityManagerFactory(
            @Qualifier("exasolDatasource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaVendorAdapter(getExasolJpaHibernateVendorAdapter());
        em.setPersistenceUnitName("otherExasol");
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Bean
    public HibernateJpaVendorAdapter getExasolJpaHibernateVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("com.stingion.yaypay.data.repository.ExasolDialect");
        return adapter;
    }

    @Bean("exasolTransactionManager")
    public PlatformTransactionManager exasolTransactionManager(
            @Qualifier("exasolEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
