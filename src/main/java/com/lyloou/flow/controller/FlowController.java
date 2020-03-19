package com.lyloou.flow.controller;

import com.lyloou.common.status.Result;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.common.util.StringUtil;
import com.lyloou.common.util.TimeUtil;
import com.lyloou.flow.mapper.FlowMapper;
import com.lyloou.flow.mapper.UserMapper;
import com.lyloou.flow.model.flow.Flow;
import com.lyloou.flow.model.flow.FlowReq;
import com.lyloou.flow.model.flow.User;
import com.lyloou.flow.model.flow.UserPassword;
import com.lyloou.flow.service.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.lyloou.common.status.StatusCodeDict.*;

/**
 * @author lyloou
 */
@RestController
@RequestMapping(path = "${apiVersion}/flow")
public class FlowController {

    private static final Logger logger = LoggerFactory.getLogger(FlowController.class);
    public static final int MAX_SYNC_NUMBER = 10;

    @Autowired
    private ResultHandler resultHandler;

    @Autowired
    FlowMapper flowMapper;

    @GetMapping("/list")
    public Result list(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("UserId") Long userId,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) {
        validator.validate(authorization, userId);

        List<Flow> flows = flowMapper.listFlow(userId, limit, offset);
        return resultHandler.dataResult(() -> COMMON_OK, flows);
    }

    @Autowired
    Validator validator;

    @GetMapping("/get")
    public Result get(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("UserId") Long userId,
            @RequestParam(value = "day", defaultValue = "") String day) {
        validator.validate(authorization, userId);

        if (StringUtil.isEmpty(day)) {
            day = TimeUtil.today();
        }
        logger.info("hello {}, how are you?", userId);
        Flow flow = flowMapper.getFlow(userId, day);
        return resultHandler.dataResult(() -> COMMON_OK, flow);
    }

    @Autowired
    UserMapper userMapper;

    @PostMapping("login")
    public Result login(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        UserPassword userPassword = userMapper.getUserPasswordByNamePassword(name, password);
        if (userPassword == null) {
            return resultHandler.dataResult(() -> PARAM_LOGIN_ERROR, null);
        }
        User user = userMapper.getUser(userPassword.getUserId());
        return resultHandler.dataResult(() -> COMMON_OK, user);
    }

    @PostMapping("/sync")
    public Result sync(
            @RequestHeader("Authorization") String authorization,
            @RequestBody FlowReq flowReq) {
        validator.validate(authorization, flowReq.getUserId());

        int i = flowMapper.syncFlow(Flow.builder()
                .userId(flowReq.getUserId())
                .day(flowReq.getDay())
                .item(flowReq.getItem())
                .weather(flowReq.getWeather())
                .memo(flowReq.getMemo())
                .isArchived(flowReq.isArchived())
                .isDisabled(flowReq.isDisabled())
                .build());
        return resultHandler.msgResult(() -> i >= 0 ? COMMON_OK : COMMON_UNKNOWN);
    }

    @PostMapping("/batch_sync")
    public Result batchSync(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("UserId") Long userId,
            @RequestBody List<FlowReq> flowReqs) {
        validator.validate(authorization, userId);

        if (flowReqs.size() > MAX_SYNC_NUMBER) {
            return resultHandler.msgResult(() -> PARAM_BEYOND_QUANTITY_NUMBER);
        }
        int i = flowMapper.batchSyncFlow(flowReqs.stream()
                .map(flowReq -> Flow.builder().day(flowReq.getDay())
                        .userId(flowReq.getUserId())
                        .item(flowReq.getItem())
                        .weather(flowReq.getWeather())
                        .memo(flowReq.getMemo())
                        .isArchived(flowReq.isArchived())
                        .isDisabled(flowReq.isDisabled())
                        .build())
                .collect(Collectors.toList())
        );
        return resultHandler.msgResult(() -> i >= 0 ? COMMON_OK : COMMON_UNKNOWN);
    }
}
