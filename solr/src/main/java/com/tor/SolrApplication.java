package com.tor;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.Properties;

/*通过在启动类禁用Solr的自动配置即可 exclude = SolrAutoConfiguration.class */
@SpringBootApplication(exclude = SolrAutoConfiguration.class)
@MapperScan("com.tor.*.mapper")//将项目中对应的mapper类的路径加进来就可以了
public class SolrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolrApplication.class, args);
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		/**默认是普通模式，会返回所有的验证不通过信息集合*/
		return new MethodValidationPostProcessor();
	}
}
