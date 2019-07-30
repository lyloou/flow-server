package com.lyloou.demo.exception;


import com.google.common.base.Throwables;
import com.lyloou.common.exception.CommonException;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.common.status.exception.CommonApiStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.lyloou.demo.Constants.*;

// [SpringMVC重要注解（二）@ControllerAdvice - Franco的博客 - CSDN博客](https://blog.csdn.net/w372426096/article/details/78429141)
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

        if (throwable instanceof CommonApiStatusException) {
            CommonApiStatusException statusException = (CommonApiStatusException) throwable;
            return resultHandler.msgResult(statusException.getStatusCode());
        } else if (ex instanceof CommonException) {
            CommonException exception = (CommonException) throwable;
            logger.error(exception.getMessage(), exception);
            return resultHandler.msgResult(() -> STATUS_ERROR_UNDEFINED);
        } else if (throwable instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException exception = (MissingServletRequestParameterException) throwable;
            return resultHandler.dataResult(() -> STATUS_ERROR_PARAM, exception.getMessage());
        } else if (throwable instanceof IllegalStateException) {
            IllegalStateException exception = (IllegalStateException) throwable;
            logger.error(exception.getMessage(), exception);
            return resultHandler.msgResult(() -> STATUS_UNKNOWN);
        } else if (throwable instanceof IllegalArgumentException) {
            IllegalArgumentException exception = (IllegalArgumentException) throwable;
            logger.error(exception.getMessage(), exception);
            return resultHandler.msgResult(() -> STATUS_UNKNOWN);
        } else if (throwable instanceof NullPointerException) {
            NullPointerException exception = (NullPointerException) throwable;
            logger.error(exception.getMessage(), exception);
            return resultHandler.msgResult(() -> STATUS_UNKNOWN);
        } else {
            logger.error(throwable.getMessage(), throwable);
            return resultHandler.msgResult(() -> STATUS_UNKNOWN);
        }
    }
}
