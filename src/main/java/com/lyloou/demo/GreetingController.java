package com.lyloou.demo;

import com.lyloou.common.exception.CommonException;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.common.status.resp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.LongAdder;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final LongAdder counter = new LongAdder();

    @Autowired
    private ResultHandler resultHandler;

    @RequestMapping("/greeting")
    public Result greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        counter.increment();
        Greeting greeting = new Greeting(counter.longValue(),
                String.format(template, name));
        return resultHandler.dataResult(() -> Constants.STATUS_OK, greeting);
    }

    @RequestMapping("/exception")
    public Result exception() {
        throw new CommonException("asdfasfasdfas");
    }
}
