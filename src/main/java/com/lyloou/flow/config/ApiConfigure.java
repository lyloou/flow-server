package com.lyloou.flow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class ApiConfigure implements WebMvcConfigurer {

    @Autowired
    private Environment environment;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (Arrays.stream(environment.getActiveProfiles())
                .anyMatch(env -> "test".equalsIgnoreCase(env) || env.equalsIgnoreCase("dev"))) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                    .allowCredentials(true).maxAge(3600);
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        ViewControllerHelper.handle(registry);
    }

    @Bean
    public DemoHandlerInterceptor demoHandlerInterceptor() {
        return new DemoHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoHandlerInterceptor());
    }
}
