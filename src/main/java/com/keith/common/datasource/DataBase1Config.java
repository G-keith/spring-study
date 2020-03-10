package com.keith.common.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author keith
 * @version 1.0
 * @date 2019/8/19
 */
@Configuration
@MapperScan(basePackages = "com.keith.project.mapper.database1", sqlSessionTemplateRef  = "database1SqlSessionTemplate")
public class DataBase1Config {

    @Bean(name = "database1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.database1")
    public DataSource database1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "database1SqlSessionFactory")
    public SqlSessionFactory database1SqlSessionFactory(@Qualifier("database1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCallSettersOnNulls(true);
        bean.setConfiguration(configuration);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/database1/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "database1TransactionManager")
    public DataSourceTransactionManager database1TransactionManager(@Qualifier("database1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "database1SqlSessionTemplate")
    public SqlSessionTemplate database1SqlSessionTemplate(@Qualifier("database1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
