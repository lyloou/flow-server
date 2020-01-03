package com.lyloou.flow.config.staticload;


import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author lyloou
 */
public class JedisPoolWithStaticLoad {

    private static final JedisPool pool;

    static {

        String server = Config.getValue("redis.server");
        int port = Integer.parseInt(Config.getValue("redis.port"));
        String password = Config.getValue("redis.password");
        int timeout = Integer.parseInt(Config.getValue("redis.timeout"));
        int maxTotal = Integer.parseInt(Config.getValue("redis.maxTotal"));

        JedisPoolConfig config = new JedisPoolConfig();
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        config.setMaxTotal(maxTotal);

        pool = new JedisPool(config, server, port, timeout, password);
        LoggerFactory.getLogger(JedisPoolWithStaticLoad.class).info("初始化 --- JedisPool");
    }

    /**
     * 获取连接
     */
    public static Jedis get() {
        synchronized (JedisPoolWithStaticLoad.class) {
            return pool.getResource();
        }
    }

}
