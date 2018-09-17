package com.xinyan.message.center.web.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by fugang on 2018/3/21.
 */
@Configuration
@MapperScan(basePackages = {"com.xinyan.message.center.dal.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryPushMessage")
public class MybatisDbConfig {

    @Autowired
    @Qualifier("pmDataSource")
    private DataSource pmDataSource;


    @Bean
    public SqlSessionFactory sqlSessionFactoryPushMessage() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(pmDataSource); // 使用pushMessage数据源
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplatePushMessage() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryPushMessage()); // 使用上面配置的Factory
        return template;
    }
}
