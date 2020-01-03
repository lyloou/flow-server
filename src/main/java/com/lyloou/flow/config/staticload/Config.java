package com.lyloou.flow.config.staticload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lyloou
 */
public class Config {

    private static final Logger LOG = LoggerFactory.getLogger(Config.class);
    private static Properties prop;

    static {
        try {
            // 配置文件路径
            String configFilePath = System.getProperty("user.dir") + "/src/main/resources/my.properties";
            InputStream is = new java.io.FileInputStream(configFilePath);
            prop = new Properties();
            prop.load(is);

        } catch (IOException e) {
            LOG.error("load config.properties error!");
        }
    }

    /**
     * 获取配置文件中的值
     */
    public static String getValue(String key) {
        return prop.getProperty(key).trim();
    }

    /**
     * 获取多个配置值
     */
    public static Object[] getValues(Object[] key) {
        int length = key.length;
        Object[] objs = new Object[length];
        for (int i = 0; i < length; i++) {
            objs[i] = prop.getProperty(key[i].toString()).trim();
        }
        return objs;
    }


    public static void main(String[] args) {
        Object[] obj = getValues(new Object[]{"my.flow.key", "my.flow.password"});
        System.out.println(obj[0] + " " + obj[1]);
    }
}
