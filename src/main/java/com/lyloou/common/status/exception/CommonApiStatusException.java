package com.lyloou.common.status.exception;


import com.lyloou.common.exception.CommonException;
import com.lyloou.common.status.StatusCode;

import java.util.ArrayList;
import java.util.List;

public class CommonApiStatusException extends CommonException {

    private final StatusCode statusCode;

    private final List<StatusCode> innerStatusCode = new ArrayList<>();

    public CommonApiStatusException(StatusCode statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public CommonApiStatusException(String s, StatusCode statusCode) {
        super(s);
        this.statusCode = statusCode;
    }

    public CommonApiStatusException(String s, StatusCode statusCode, Throwable throwable) {
        super(s, throwable);
        this.statusCode = statusCode;
    }

    public CommonApiStatusException appendInnerStatusCode(StatusCode innerStatusCode) {
        this.innerStatusCode.add(innerStatusCode);
        return this;
    }

    public List<StatusCode> getInnerStatusCodes() {
        return innerStatusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
    

}
