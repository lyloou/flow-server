package com.lyloou.common.status;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty(prefix = "api.status.enable",value = "true",matchIfMissing = true)
@EnableConfigurationProperties(ApiStatusProps.class)
@ConditionalOnClass(ResultHandler.class)
public class ApiStatusAutoConfiguration {

    @Bean
    public ResultHandler resultHandler(ApiStatusProps apiStatusProps) {
        ResultHandler resultHandler = new ResultHandler();
        resultHandler.setApiStatusProps(apiStatusProps);
        return resultHandler;
    }

}
