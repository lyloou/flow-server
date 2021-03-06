package com.lyloou.flow.controller;

import com.lyloou.common.status.Result;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.common.util.StringUtil;
import com.lyloou.common.util.TimeUtil;
import com.lyloou.flow.mapper.FlowMapper;
import com.lyloou.flow.model.flow.Flow;
import com.lyloou.flow.model.flow.FlowReq;
import com.lyloou.flow.service.UserService;
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
        userService.validate(authorization, userId);

        List<Flow> flows = flowMapper.listFlow(userId, limit, offset);
        return resultHandler.dataResult(() -> COMMON_OK, flows);
    }

    @Autowired
    UserService userService;

    @GetMapping("/get")
    public Result get(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("UserId") Long userId,
            @RequestParam(value = "day", defaultValue = "") String day) {
        userService.validate(authorization, userId);

        if (StringUtil.isEmpty(day)) {
            day = TimeUtil.today();
        }
        logger.info("hello {}, how are you?", userId);
        Flow flow = flowMapper.getFlow(userId, day);
        return resultHandler.dataResult(() -> COMMON_OK, flow);
    }

    @Autowired
    UserController userController;

    /**
     * 兼容 v1
     *
     * @param name
     * @param password
     * @return
     */
    @Deprecated
    @PostMapping("login")
    public Result login(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        return userController.login(name, password);
    }

    @PostMapping("/sync")
    public Result sync(
            @RequestHeader("Authorization") String authorization,
            @RequestBody FlowReq flowReq) {
        userService.validate(authorization, flowReq.getUserId());

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
        userService.validate(authorization, userId);

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
