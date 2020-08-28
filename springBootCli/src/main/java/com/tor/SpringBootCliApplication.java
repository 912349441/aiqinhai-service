package com.tor;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.tor.common.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;

/**
 * @author Tzx
 */
@Slf4j
@SpringBootApplication(exclude = {
        DruidDataSourceAutoConfigure.class,
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class
})
public class SpringBootCliApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCliApplication.class, args);
        printProjectConfigs();
    }

    private static void printProjectConfigs() {
        ServerProperties serverProperties = SpringContextHolder.getApplicationContext().getBean(ServerProperties.class);
        String contextPath = serverProperties.getServlet().getContextPath();
        log.info("==================> run at http://127.0.0.1:{}/swagger-ui.html  <==================", serverProperties.getPort());
    }
}
