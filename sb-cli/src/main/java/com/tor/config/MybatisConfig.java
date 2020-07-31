package com.tor.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Slf4j
public class MybatisConfig {

    @Configuration
    @MapperScan(basePackages = {"com.tor.project.mapper.primary"},
            /*sqlSessionFactoryRef = "sqlSessionFactoryPrimary",*/
            sqlSessionTemplateRef = "sqlSessionTemplatePrimary")
    public static class DataSourcePrimary{
        @Autowired
        @Qualifier(DataSourceConfig.DATASOURCE_PRIMARY)
        DataSource dataSourcePrimary;

        @Bean
        public SqlSessionFactory sqlSessionFactoryPrimary() throws Exception {
            log.info("初始化主数据源");
            MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
            MybatisConfiguration configuration = new MybatisConfiguration();
            configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
            configuration.setJdbcTypeForNull(JdbcType.NULL);
            factoryBean.setConfiguration(configuration);
            //指定xml路径.
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:mapper/primary/*.xml"));
            factoryBean.setPlugins(new Interceptor[]{
                    new PaginationInterceptor(),
                    new PerformanceInterceptor(),
                    new OptimisticLockerInterceptor()
            });
            factoryBean.setDataSource(dataSourcePrimary);
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
            /*sqlSessionFactoryRef = "sqlSessionFactorySecond",*/
            sqlSessionTemplateRef ="sqlSessionTemplateSecond" )
    public static class DataSourceSecond{
        @Autowired
        @Qualifier(DataSourceConfig.DATASOURCE_SECOND)
        DataSource dataSourceSecond;

        @Bean
        public SqlSessionFactory sqlSessionFactorySecond() throws Exception {
            log.info("初始化第二数据源");
            MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
            MybatisConfiguration configuration = new MybatisConfiguration();
            configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
            configuration.setJdbcTypeForNull(JdbcType.NULL);
            factoryBean.setConfiguration(configuration);
            //指定xml路径.
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:mapper/second/*.xml"));
            factoryBean.setPlugins(new Interceptor[]{
                    new PaginationInterceptor(),
                    new PerformanceInterceptor(),
                    new OptimisticLockerInterceptor()
            });
            factoryBean.setDataSource(dataSourceSecond);
            return factoryBean.getObject();

        }

        @Bean
        public SqlSessionTemplate sqlSessionTemplateSecond() throws Exception {
            // 使用上面配置的Factory
            return new SqlSessionTemplate(sqlSessionFactorySecond());
        }
    }
}
