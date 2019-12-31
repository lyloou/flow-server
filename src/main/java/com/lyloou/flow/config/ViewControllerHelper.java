package com.lyloou.flow.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyloou
 * @date 2019/08/01
 */
class ViewControllerHelper {
    private static Map<String, String> MAPS = new HashMap<String, String>() {{
        put("/index", "index");
        put("/index2", "index2");
    }};

    public static void handle(ViewControllerRegistry registry) {
        MAPS.forEach((urlPath, viewName) -> registry.addViewController(urlPath).setViewName(viewName));
    }
}
