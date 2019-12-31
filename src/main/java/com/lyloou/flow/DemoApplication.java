package com.lyloou.flow;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lyloou
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lyloou.flow", "com.lyloou.common"})
@MapperScan("com.lyloou.flow.mapper")
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        addShutdownHook();
        SpringApplication.run(DemoApplication.class, args);

    }

    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.error("system shutdown");
        }));
    }
}
