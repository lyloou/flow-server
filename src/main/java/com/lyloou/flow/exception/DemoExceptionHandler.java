package com.lyloou.flow.exception;


import com.google.common.base.Throwables;
import com.lyloou.common.exception.ApiStatusException;
import com.lyloou.common.exception.CommonException;
import com.lyloou.common.exception.ParamException;
import com.lyloou.common.status.ResultHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.lyloou.common.status.StatusCodeDict.*;

/**
 * [SpringMVC重要注解（二）@ControllerAdvice - Franco的博客 - CSDN博客](https://blog.csdn.net/w372426096/article/details/78429141)
 *
 * @author lyloou
 * @date 2019/08/01
 */


@ControllerAdvice
public class DemoExceptionHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ResultHandler resultHandler;

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    Object commonExceptionHandler(Exception ex) {
        if (logger.isDebugEnabled()) {
            logger.debug(ex.getMessage(), ex);
        }
        Throwable throwable = Throwables.getRootCause(ex);

        if (throwable instanceof ParamException) {
            ParamException statusException = (ParamException) throwable;
            return resultHandler.msgResult(() -> PARAM).appendMsg(statusException.getMessage());
        } else if (throwable instanceof ApiStatusException) {
            ApiStatusException statusException = (ApiStatusException) throwable;
            return resultHandler.msgResult(statusException.getStatusCode());
        } else if (ex instanceof CommonException) {
            CommonException exception = (CommonException) throwable;
            logger.error(exception.getMessage(), exception);
            return resultHandler.msgResult(() -> UNDEFINED);
        } else if (throwable instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException exception = (MissingServletRequestParameterException) throwable;
            return resultHandler.msgResult(() -> PARAM).appendMsg(exception.getMessage());
        } else if (throwable instanceof IllegalStateException) {
            IllegalStateException exception = (IllegalStateException) throwable;
            logger.error(exception.getMessage(), exception);
            return resultHandler.msgResult(() -> COMMON_UNKNOWN);
        } else if (throwable instanceof IllegalArgumentException) {
            IllegalArgumentException exception = (IllegalArgumentException) throwable;
            logger.error(exception.getMessage(), exception);
            return resultHandler.msgResult(() -> COMMON_UNKNOWN);
        } else if (throwable instanceof NullPointerException) {
            NullPointerException exception = (NullPointerException) throwable;
            logger.error(exception.getMessage(), exception);
            return resultHandler.msgResult(() -> COMMON_UNKNOWN);
        } else if (throwable instanceof MissingRequestHeaderException) {
            MissingRequestHeaderException exception = (MissingRequestHeaderException) throwable;
            logger.error(exception.getMessage(), exception);
            return resultHandler.msgResult(() -> COMMON_INVALID_REQUEST).appendMsg(exception.getMessage());
        } else {
            logger.error(throwable.getMessage(), throwable);
            return resultHandler.msgResult(() -> COMMON_UNKNOWN);
        }
    }
}
