package com.kshrd.kd.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlerConfiguration implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/static/dist/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui").setViewName("/swagger/index");
        registry.addViewController("/login").setViewName("admin/login");
        registry.addViewController("/accessdenied").setViewName("admin/accessdeny");
    }
}
