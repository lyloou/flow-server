package com.lyloou.flow;

import com.lyloou.flow.mapper.EventMapper;
import com.lyloou.flow.mapper.FlowMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MapperScan("com.lyloou.flow.mapper")
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

}
