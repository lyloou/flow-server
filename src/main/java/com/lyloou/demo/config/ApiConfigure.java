package com.lyloou.demo.config;

import com.lyloou.common.status.ApiStatusAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
@Import(ApiStatusAutoConfiguration.class)
public class ApiConfigure extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment environment;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (Arrays.stream(environment.getActiveProfiles())
                .anyMatch(env -> env.equalsIgnoreCase("test") || env.equalsIgnoreCase("dev"))) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                    .allowCredentials(true).maxAge(3600);
        }
    }


}
