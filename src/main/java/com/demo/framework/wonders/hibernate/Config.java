package com.demo.framework.wonders.hibernate;

/**
 * Created by Krishan Shukla on 11/07/2018.
 */
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
//@PropertySource("classpath:application.properties")
@PropertySource("application.properties")
@CrossOrigin
@EnableJpaRepositories(basePackages={"com.gfk.framework.wonders.repository"})
public class Config {

    @Autowired
    private Environment env;

    @Value("${entitymanager.packages.to.scan}")
    public String ENTITY_MANAGER_PACKAGE_TO_SCAN;

    @Value("${location.application.property}")
    private String LOCATION_APPLICATION_PROPERTIES;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    public String HIBERNATE_HBM2DDl_AUTO;

    @Value("${spring.jpa.hibernate.dialect}")
    public String HIBERNATE_DIALECT;

    @Value("${spring.jpa.hibernate.show-sql}")
    public String HIBERNATE_SHOW_SQL;

    @Value("${spring.datasource.driver}")
    private String DB_DRIVER;

    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String DB_USERNAME;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.mykitchen.tastytiffin.entity" });
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setJpaProperties(additionalProperties());

        return em;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        System.out.println("I am in env shyam - " + LOCATION_APPLICATION_PROPERTIES);
        properties.setProperty("hibernate.hbm2ddl.auto",HIBERNATE_HBM2DDl_AUTO);
        properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        return properties;
    }


}
