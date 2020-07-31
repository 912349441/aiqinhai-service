package com.tor.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
public class MybatisConfig {

    @Configuration
    @MapperScan(basePackages = {"com.tor.project.mapper.primary"},
            sqlSessionFactoryRef = "sqlSessionFactoryPrimary",
            sqlSessionTemplateRef = "sqlSessionTemplatePrimary")
    public static class DataSourcePrimary{
        @Resource
        DataSource dataSourcePrimary;

        @Bean
        public SqlSessionFactory sqlSessionFactoryPrimary() throws Exception {
            log.info("初始化主数据源");
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSourcePrimary);
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:mapper/primary/*.xml"));
            return factoryBean.getObject();

        }

        @Bean
        public SqlSessionTemplate sqlSessionTemplatePrimary() throws Exception {
            // 使用上面配置的Factory
            return new SqlSessionTemplate(sqlSessionFactoryPrimary());
        }
    }

    @Configuration
    @MapperScan(basePackages = {"com.tor.project.mapper.second"},
            sqlSessionFactoryRef = "sqlSessionFactorySecond",
            sqlSessionTemplateRef ="sqlSessionTemplateSecond" )
    public static class DataSourceSecond{
        @Resource
        DataSource dataSourceSecond;

        @Bean
        public SqlSessionFactory sqlSessionFactorySecond() throws Exception {
            log.info("初始化第二数据源");
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSourceSecond);
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:mapper/second/*.xml"));
            return factoryBean.getObject();

        }

        @Bean
        public SqlSessionTemplate sqlSessionTemplateSecond() throws Exception {
            // 使用上面配置的Factory
            return new SqlSessionTemplate(sqlSessionFactorySecond());
        }
    }
}
