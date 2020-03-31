package com.lyloou.flow.controller;

import com.lyloou.common.status.Result;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.flow.mapper.ScheduleMapper;
import com.lyloou.flow.model.schedule.Schedule;
import com.lyloou.flow.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lyloou.common.status.StatusCodeDict.*;

/**
 * @author lyloou
 */
@RestController
@RequestMapping(path = "${apiVersion}/schedule")
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    public static final int MAX_SYNC_NUMBER = 10;

    @Autowired
    private ResultHandler resultHandler;

    @Autowired
    ScheduleMapper scheduleMapper;

    @GetMapping("/list")
    public Result list(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("UserId") Long userId,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) {
        userService.validate(authorization, userId);

        List<Schedule> schedules = scheduleMapper.listSchedule(userId, limit, offset);
        return resultHandler.dataResult(() -> COMMON_OK, schedules);
    }

    @Autowired
    UserService userService;

    @PostMapping("/batch_sync")
    public Result batchSync(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("UserId") Long userId,
            @RequestBody List<Schedule> schedules) {
        userService.validate(authorization, userId);

        if (schedules.size() > MAX_SYNC_NUMBER) {
            return resultHandler.msgResult(() -> PARAM_BEYOND_QUANTITY_NUMBER);
        }
        int num = scheduleMapper.batchSyncSchedule(schedules);
        return resultHandler.msgResult(() -> num >= 0 ? COMMON_OK : COMMON_UNKNOWN);
    }

    @PostMapping("/batch_delete")
    public Result batchDelete(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("UserId") Long userId,
            @RequestBody List<Long> ids) {
        userService.validate(authorization, userId);

        int num = scheduleMapper.batchDeleteSchedule(ids);
        return resultHandler.msgResult(() -> num >= 0 ? COMMON_OK : COMMON_UNKNOWN);
    }
}
