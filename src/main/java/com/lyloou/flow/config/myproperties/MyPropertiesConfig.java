package com.lyloou.flow.config.myproperties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author lyloou
 */
@Configuration
@PropertySource(value = {"classpath:my.properties"})
@ComponentScan
public class MyPropertiesConfig {
}
