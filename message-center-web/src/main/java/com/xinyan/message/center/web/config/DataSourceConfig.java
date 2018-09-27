package com.xinyan.message.center.web.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by fugang on 2018/3/21.
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "pmDataSource")
    @ConfigurationProperties(prefix = "credit-push-govern.jdbc")
    public DataSource pmDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

}
