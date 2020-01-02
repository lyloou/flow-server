package com.lyloou.flow.controller;

import com.lyloou.common.status.Result;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.common.util.StringUtil;
import com.lyloou.common.util.TimeUtil;
import com.lyloou.flow.mapper.FlowMapper;
import com.lyloou.flow.model.flow.Flow;
import com.lyloou.flow.model.flow.FlowReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lyloou.common.status.StatusCodeDict.COMMON_OK;
import static com.lyloou.common.status.StatusCodeDict.COMMON_UNKNOWN;

/**
 * @author lyloou
 */
@RestController
@RequestMapping(path = "${apiVersion}/flow")
public class FlowController {

    private static final Logger logger = LoggerFactory.getLogger(FlowController.class);

    @Autowired
    private ResultHandler resultHandler;

    @Autowired
    FlowMapper flowMapper;

    @RequestMapping("/list")
    public Result list(@RequestParam(value = "limit", defaultValue = "10") int limit, @RequestParam(value = "offset", defaultValue = "0") int offset) {
        List<Flow> flows = flowMapper.listFlow(limit, offset);
        return resultHandler.dataResult(() -> COMMON_OK, flows);
    }

    @RequestMapping("/get")
    public Result get(@RequestParam(value = "day", defaultValue = "") String day) {
        if (StringUtil.isEmpty(day)) {
            day = TimeUtil.today();
        }
        Flow flow = flowMapper.getFlow(day);
        return resultHandler.dataResult(() -> COMMON_OK, flow);
    }

    @PostMapping("/sync")
    public Result sync(@RequestBody FlowReq flowReq) {
        int i = flowMapper.insertOrUpdateFlow(Flow.builder()
                .day(flowReq.getDay())
                .item(flowReq.getItem())
                .isArchived(flowReq.isArchived())
                .isDisabled(flowReq.isDisabled())
                .build());
        return resultHandler.msgResult(() -> i >= 0 ? COMMON_OK : COMMON_UNKNOWN);
    }

}
