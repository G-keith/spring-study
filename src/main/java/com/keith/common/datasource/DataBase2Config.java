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
@MapperScan(basePackages = "com.keith.project.mapper.database2", sqlSessionTemplateRef  = "database2SqlSessionTemplate")
public class DataBase2Config {

    @Bean(name = "database2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.database2")
    @Primary
    public DataSource database2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "database2SqlSessionFactory")
    @Primary
    public SqlSessionFactory database2SqlSessionFactory(@Qualifier("database2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCallSettersOnNulls(true);
        bean.setConfiguration(configuration);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/database2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "database2TransactionManager")
    @Primary
    public DataSourceTransactionManager database2TransactionManager(@Qualifier("database2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "database2SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate database2SqlSessionTemplate(@Qualifier("database2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
