package com.mycollab.spring.test.service;

import com.mycollab.test.service.DataSourceFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
@Configuration
@Profile("test")
@MapperScan("com.mycollab.**.dao")
public class DataSourceConfigurationTest {

    @Bean
    public DataSource dataSource() {
        return new DataSourceFactoryBean().getDataSource();
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        DataSourceTransactionManager bean = new DataSourceTransactionManager();
        bean.setDataSource(dataSource());
        return bean;
    }
}
