package com.lyloou.flow.properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class PropertiesBean {
    public enum My {
        /**
         *
         */
        FLOW_KEY
    }

    @Value("${my.flow.key}")
    private String flowKey = null;


    @Bean
    @Qualifier("myProperties")
    public Properties property() {
        Properties properties = new Properties();
        properties.setProperty(My.FLOW_KEY.name(), flowKey);
        return properties;
    }

}
