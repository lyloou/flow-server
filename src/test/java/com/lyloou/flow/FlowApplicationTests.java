package com.lyloou.flow;

import com.google.common.base.Joiner;
import com.lyloou.common.util.IdGenerator;
import com.lyloou.flow.config.myproperties.MyPropertiesBean;
import com.lyloou.flow.mapper.FlowMapper;
import com.lyloou.flow.mapper.ScheduleMapper;
import com.lyloou.flow.mapper.UserMapper;
import com.lyloou.flow.model.user.UserPassword;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Properties;

@RunWith(SpringRunner.class)
@MapperScan("com.lyloou.flow.mapper")
@ComponentScan(basePackages = {"com.lyloou.flow", "com.lyloou.common"})
@SpringBootTest
public class FlowApplicationTests {

    @Autowired
    FlowMapper flowMapper;

    /**
     * 数据库测试：flow
     */
    @Test
    public void testFlow() {
        System.out.println(flowMapper.getFlow(1L, null));
    }

    @Autowired
    ScheduleMapper scheduleMapper;

    /**
     * 数据库测试：event
     */
    @Test
    public void testEvent() {
        System.out.println(scheduleMapper.listSchedule(1L, 10, 0));
    }

    @Autowired
    UserMapper userMapper;

    @Test
    public void testUserPassword() {
        UserPassword userPassword = userMapper.getUserPasswordByUserId(1L);
        System.out.println(userPassword);
    }

    @Autowired
    @Qualifier("myProperties")
    Properties properties;

    /**
     * 测试自定义的 properties
     */
    @Test
    public void testProperties() {
        System.out.println(properties.getProperty(MyPropertiesBean.My.FLOW_KEY.name()));
    }

    @Autowired
    Jedis jedis;

    /**
     * 测试 jedis
     */
    @Test
    public void testJedis() {
        String cacheName = "a-key" + System.currentTimeMillis();
        String key = jedis.get(cacheName);
        assert null == key;

        String value = "hello";
        jedis.setex(cacheName, 60, value);
        key = jedis.get(cacheName);
        assert key.equals(value);
    }

    @Autowired
    IdGenerator idGenerator;

    @Test
    public void testGenerator() throws InterruptedException {
        List<String> ids = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            double delay = Math.random() * 1000;
            Thread.sleep((long) delay);
            ids.add(idGenerator.generate(IdGenerator.Type.ORDER));
        }
        System.out.println(Joiner.on(",\n").join(ids));
    }

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 测试加载 application.properties
     */
    @Test
    public void testApplicationProperties() {
        System.out.println(applicationName);
    }


}
