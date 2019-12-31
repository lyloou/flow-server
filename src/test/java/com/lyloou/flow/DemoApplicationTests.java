package com.lyloou.flow;

import com.lyloou.flow.mapper.EventMapper;
import com.lyloou.flow.mapper.FlowMapper;
import com.lyloou.flow.properties.PropertiesBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

@RunWith(SpringRunner.class)
@MapperScan("com.lyloou.flow.mapper")
@ComponentScan(basePackages = {"com.lyloou.flow", "com.lyloou.common"})
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    FlowMapper flowMapper;

    @Test
    public void testFlow() throws Exception {
        System.out.println(flowMapper.getFlow(1));
    }


    @Autowired
    EventMapper eventMapper;

    @Test
    public void testEvent() throws Exception {
        System.out.println(eventMapper.listEvent());
    }

    @Autowired
    @Qualifier("myProperties")
    Properties properties;

    @Test
    public void testProperties() {
        System.out.println("==================================");
        System.out.println(properties.getProperty(PropertiesBean.My.FLOW_KEY.name()));
        System.out.println("==================================");
    }

}
