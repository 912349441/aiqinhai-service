package com.tor;

import com.tor.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;

/**
 * @author Tzx
 */
@Slf4j
@SpringBootApplication
public class SbCliApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbCliApplication.class, args);
        printProjectConfigs();
    }

    private static void printProjectConfigs() {
        ServerProperties serverProperties = SpringContextHolder.getApplicationContext().getBean(ServerProperties.class);
        DataSourceProperties dataSourceProperties = SpringContextHolder.getApplicationContext().getBean(DataSourceProperties.class);
        log.info("数据库：{}", dataSourceProperties.getUrl());
        log.info("==================> run at http://127.0.0.1:{}/swagger-ui.html  <==================", serverProperties.getPort());
    }
}
