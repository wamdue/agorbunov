package ru.job4j.mapping.carshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created on 13.02.18.
 * Configuration file.
 * @author Wamdue
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "ru.job4j.mapping.carshop")
@EnableJpaRepositories(basePackages = "ru.job4j.mapping.carshop.model")
public class Config {
    /**
     * Factory manager bean.
     * @return - factory.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean getIntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setJpaVendorAdapter(this.getJpaVendorAdapter());
        lcemfb.setDataSource(this.dataSource());
        lcemfb.setPackagesToScan("ru.job4j.mapping.carshop.entity");
        lcemfb.setJpaProperties(this.getProperties());
        return lcemfb;
    }

    /**
     * Vendor adapter.
     * @return - adapter.
     */
    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernate = new HibernateJpaVendorAdapter();
        hibernate.setDatabase(Database.POSTGRESQL);
        hibernate.setGenerateDdl(true);
        return hibernate;
    }

    /**
     * Data source creation.
     * @return - data source.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgres.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/car_shop");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

    /**
     * Transaction manager creation.
     * @return - transaction manager.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(getIntityManagerFactoryBean().getObject());
    }

    /**
     * Properties for hibernate.
     * @return - properties.
     */
    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }
}