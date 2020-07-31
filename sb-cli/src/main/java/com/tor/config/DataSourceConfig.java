package com.tor.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    final static String DATASOURCE_PRIMARY = "dataSourcePrimary";
    final static String DATASOURCE_SECOND = "dataSourceSecond";

    @Primary
    @Bean(name = DataSourceConfig.DATASOURCE_PRIMARY)
    @Qualifier(value = DataSourceConfig.DATASOURCE_PRIMARY)
    @ConfigurationProperties(prefix = "spring.datasource.druid.primary") // application.properteis中对应属性的前缀
    public DataSource dataSourcePrimary() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DataSourceConfig.DATASOURCE_SECOND)
    @Qualifier(value = DataSourceConfig.DATASOURCE_SECOND)
    @ConfigurationProperties(prefix = "spring.datasource.druid.second") // application.properteis中对应属性的前缀
    public DataSource dataSourceSecond() {
        return DataSourceBuilder.create().build();
    }
}
