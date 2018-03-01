package com.lzkj.lxzb.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = {"com.lzkj.lxzb"})
public class LXZBRestApplication extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(LXZBRestApplication.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
       // registry.addResourceHandler("/login.html").addResourceLocations("classpath:/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
    }

    public static void main(String[] args) {

        SpringApplication.run(LXZBRestApplication.class, args);

        logger.info("LXZB Manager System start SUCCESS!!!!!");
    }
}
