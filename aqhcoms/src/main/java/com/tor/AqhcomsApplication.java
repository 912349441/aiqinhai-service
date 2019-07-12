package com.tor;


import com.tor.common.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan({"com.tor.*.mapper", "com.tor.common.generator.dao"})
// 以下三个是为了不启动Eureka服务 所以暂时关闭
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableEurekaClient
public class AqhcomsApplication {
    private static Logger log = LoggerFactory.getLogger(AqhcomsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AqhcomsApplication.class, args);
        printProjectConfigs();
    }

    private static void printProjectConfigs() {
        ServerProperties serverProperties = SpringContextHolder.getApplicationContext().getBean(ServerProperties.class);
        DataSourceProperties dataSourceProperties = SpringContextHolder.getApplicationContext().getBean(DataSourceProperties.class);
        final String port = serverProperties.getPort() + serverProperties.getContextPath();
        log.info("数据库：{}", dataSourceProperties.getUrl());
        log.info("====> run at http://localhost:{} or run at http://localhost:{}/swagger-ui.html <====", port,port);
    }
}