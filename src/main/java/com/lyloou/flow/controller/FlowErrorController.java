package com.lyloou.flow.controller;


import com.lyloou.common.status.ResultHandler;
import com.lyloou.common.status.StatusCodeDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FlowErrorController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowErrorController.class);
    private static final String ERROR_HANDLER = "/error";

    @Autowired
    private ResultHandler resultHandler;

    @RequestMapping(ERROR_HANDLER)
    public @ResponseBody
    Object handleError(HttpServletRequest request) {
        int httpCode = (int) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        if (LOGGER.isDebugEnabled() && null != exception) {
            LOGGER.debug(exception.getMessage(), exception);
        }
        return resultHandler.msgResult(() -> StatusCodeDict.of(httpCode));
    }

    @Override
    public String getErrorPath() {
        return ERROR_HANDLER;
    }
}
