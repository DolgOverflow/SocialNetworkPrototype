package testgroup.filmography.config;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "testgroup.filmography")
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")

public class HibernateConfig {
    private Environment enviroment;

    @Autowired
    public void setEnviroment(Environment enviroment) {
        this.enviroment = enviroment;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", enviroment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", enviroment.getRequiredProperty("hibernate.show_sql"));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(enviroment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(enviroment.getRequiredProperty("jdbc.database.url"));
        dataSource.setUsername(enviroment.getRequiredProperty("jdbc.database.username"));
        dataSource.setPassword(enviroment.getRequiredProperty("jdbc.database.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("testgroup.filmography.model");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
