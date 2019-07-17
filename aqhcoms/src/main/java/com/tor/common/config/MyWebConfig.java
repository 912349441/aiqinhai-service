package com.tor.common.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {
    private Logger log = Logger.getLogger(MyWebConfig.class);

    @Value(value = "${ty.vendorPath}")
    private String vendorPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("MyWebConfig : 配置 addResourceLocations file:"+ vendorPath);
        registry.addResourceHandler("/vendor/**").addResourceLocations("file:"+vendorPath);
        super.addResourceHandlers(registry);
    }
}