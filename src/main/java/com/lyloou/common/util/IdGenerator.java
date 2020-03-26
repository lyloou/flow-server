package com.lyloou.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * ID 生成器
 */
@Component
public class IdGenerator {
    /**
     * 生成器类型
     */
    public enum Type {
        NORMAL(""),
        ORDER("OR"),
        PAYMENT("PM"),
        WALLET("WA"),
        RETURN("RT"),
        REFUND("RF");

        private String prefix;

        Type(String prefix) {
            this.prefix = prefix;
        }
    }

    private static String GENERATOR = "GENERATOR";
    private static String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static String FORMAT_1 = "yyyyMMddHHmmss";
    private static String FORMAT_DATE = "yyyyMMdd";

    public String generate(Type type) {
        String now = DateFormatUtils.format(new Date(), FORMAT_1);
        String key = GENERATOR + ":" + type.name();
        int counter = getCounter(key);
        return String.format("%s%s%s", type.prefix, now, counter);
    }

    @Autowired
    Jedis jedis;

    /**
     * 通过 redis 来获取累加器
     * 根据 key 类型来获取累加数字
     *
     * @param key 类型
     * @return 累加的数字
     */
    private int getCounter(String key) {
        int num;
        String serial = jedis.get(key);
        num = 1000;
        if (serial != null && Integer.parseInt(serial) < 9999) {
            num = jedis.incrBy(key, 1).intValue();
        } else {
            jedis.set(key, String.valueOf(num));
        }
        return num;
    }
}
