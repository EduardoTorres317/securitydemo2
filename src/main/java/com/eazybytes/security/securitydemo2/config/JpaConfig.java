package com.eazybytes.security.securitydemo2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
public class JpaConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.gcpurl}")
    private String gcpUrl;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String sqlDialect;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.gcpusername}")
    private String gcpdDbUsername;


    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    @Profile("default")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(dbUrl);
        ds.setUsername(dbUsername);
        ds.setPassword(dbPassword);
        return ds;
    }


    @Bean
    @Profile("production")
    public DataSource gcpDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(gcpUrl);
        ds.setUsername(gcpdDbUsername);
        ds.setPassword(dbPassword);
        return ds;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.eazybytes.security.securitydemo2.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.dialect", sqlDialect);
        jpaProperties.put("hibernate.show_sql", "true");
        emf.setJpaProperties(jpaProperties);

        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }


}


