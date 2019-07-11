package com.tor;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan({"com.tor.*.mapper","com.tor.*.*.dao"})
// 以下三个是为了不启动Eureka服务 所以暂时关闭
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableEurekaClient
public class AqhcomsApplication{

   public static void main(String[] args) {
      SpringApplication.run(AqhcomsApplication.class, args);
      run();
   }

    public static void run(){

    }
}