package com.lyloou.flow.controller;

import com.lyloou.common.exception.ParamException;
import com.lyloou.common.status.Result;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.flow.mapper.FlowMapper;
import com.lyloou.flow.model.flow.Flow;
import com.lyloou.flow.model.greeting.GreetingVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.LongAdder;

import static com.lyloou.common.status.StatusCodeDict.COMMON_OK;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final LongAdder counter = new LongAdder();

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private ResultHandler resultHandler;

    @Autowired
    FlowMapper flowMapper;

    @RequestMapping("/greeting")
    public Result greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.info("logger-info: {}, more message", "param");
        logger.debug("logger-debug: {}, more message", "param");
        logger.warn("logger-warn: {}, more message", "param");
        logger.error("logger-error: {}, more message", "param");
        counter.increment();
        GreetingVO greetingVO = new GreetingVO(counter.longValue(),
                String.format(template, name));
        Flow users = flowMapper.getFlow(1);
        System.out.println(users);
        return resultHandler.dataResult(() -> COMMON_OK, greetingVO);
    }

    @RequestMapping("/exception")
    public Result exception() {
        throw new ParamException("输入有误，请重新输入");
    }

    @RequestMapping("sayHi")
    public Result sayHi(HttpServletRequest request, HttpServletResponse response) {
        Object hi = request.getAttribute("sayHi");
        if (hi != null) {
            logger.info("from HandlerInterceptor.preHandle.sayHi--->{}", hi);
        }
        return resultHandler.dataResult(() -> COMMON_OK, hi);
    }
}
