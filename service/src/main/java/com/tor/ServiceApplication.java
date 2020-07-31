package com.tor;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.tor.*.mapper")//将项目中对应的mapper类的路径加进来就可以了
public class ServiceApplication extends SpringBootServletInitializer {

   public static void main(String[] args) {
      SpringApplication.run(ServiceApplication.class, args);
   }

   @Bean
   public MethodValidationPostProcessor methodValidationPostProcessor() {
      /**默认是普通模式，会返回所有的验证不通过信息集合*/
      return new MethodValidationPostProcessor();
   }

   @Override//为了打包springboot项目
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(this.getClass());
   }
}