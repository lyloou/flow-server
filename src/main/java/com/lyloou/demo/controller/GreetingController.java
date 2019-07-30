package com.lyloou.demo.controller;

import com.lyloou.common.exception.CommonException;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.common.status.Result;
import com.lyloou.common.constant.StatusConstants;
import com.lyloou.demo.model.greeting.GreetingVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.LongAdder;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final LongAdder counter = new LongAdder();

    private static Logger logger = LoggerFactory.getLogger(GreetingController.class);
    @Autowired
    private ResultHandler resultHandler;

    @RequestMapping("/greeting")
    public Result greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.info("logger-info", "asdfasdf");
        logger.debug("logger-debug", "asdfasdf");
        logger.warn("logger-warn", "asdfasdf");
        logger.error("logger-error", "asdfasdf");
        counter.increment();
        GreetingVO greetingVO = new GreetingVO(counter.longValue(),
                String.format(template, name));
        return resultHandler.dataResult(() -> StatusConstants.STATUS_OK, greetingVO);
    }

    @RequestMapping("/exception")
    public Result exception() {
        throw new CommonException("asdfasfasdfas");
    }
}
