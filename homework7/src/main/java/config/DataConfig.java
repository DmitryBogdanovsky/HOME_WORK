package config;


import entyty.Person;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "homework7")
@PropertySource(value = {
        "classpath:/hibernate.properties",
        "classpath:/person.jdbc.properties"
})

@EnableTransactionManagement
public class DataConfig {

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.show_sql}") String showSql,
            @Value("true") String debug,
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.format_sql}") String format
    ) {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.show_sql", showSql);
        hibernateProperties.put("debug", debug);
        hibernateProperties.put("hibernate.dialect", dialect);
        hibernateProperties.put("hibernate.format_sql", format);
        hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");

        return hibernateProperties;
    }

    @Bean
    public DataSource dataSource(
            @Value("${url}") String url,
            @Value("${driver}") String driverClassName,
            @Value("root") String userName,
            @Value("${password}") String password,
            @Value("true") boolean removeAbandonedOnBorrow,
            @Value("10") int initialSize,
            @Value("25") int maxTotal) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setRemoveAbandonedOnBorrow(removeAbandonedOnBorrow);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxTotal);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource,
                                                  Properties hibernateProperties){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setAnnotatedClasses(
                Person.class
        );
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        return new HibernateTransactionManager(sessionFactory);
    }

}
