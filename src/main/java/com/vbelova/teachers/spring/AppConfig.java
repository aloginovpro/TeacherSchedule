package com.vbelova.teachers.spring;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan(value = {
        "com.vbelova.teachers.controller",
        "com.vbelova.teachers.repository",
        "com.vbelova.teachers.service"
})
@EnableJpaRepositories("com.vbelova.teachers.repository")
@Import(value = {
        SecurityConfig.class,
        WebMvcConfig.class
})
@PropertySource("classpath:db.properties")
public class AppConfig extends WebSecurityConfigurerAdapter {

    private final Environment env;

    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
        sessionBuilder.scanPackages("com.vbelova.teachers");
        sessionBuilder.addProperties(getHibernateProperties());
        return sessionBuilder.buildSessionFactory();
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(
                "com.vbelova.teachers.repository",
                "com.vbelova.teachers.entity"
        );
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        return properties;
    }

}
