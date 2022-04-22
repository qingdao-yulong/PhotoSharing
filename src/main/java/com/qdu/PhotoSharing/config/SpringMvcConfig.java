package com.qdu.PhotoSharing.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileNotFoundException;

@Configuration
@ComponentScan(basePackages = "com.qdu.PhotoSharing.controller")
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        try {
//            registry.addResourceHandler("/static/img/**")
//                    .addResourceLocations("file:" + ResourceUtils.getURL("classpath:static/img/").getPath());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
