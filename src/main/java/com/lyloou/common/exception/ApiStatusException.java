package com.lyloou.common.exception;


import com.lyloou.common.status.StatusCode;

import java.util.ArrayList;
import java.util.List;

public class ApiStatusException extends CommonException {

    private final StatusCode statusCode;

    private final List<StatusCode> innerStatusCode = new ArrayList<>();

    public ApiStatusException(StatusCode statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public ApiStatusException(String s, StatusCode statusCode) {
        super(s);
        this.statusCode = statusCode;
    }

    public ApiStatusException(String s, StatusCode statusCode, Throwable throwable) {
        super(s, throwable);
        this.statusCode = statusCode;
    }

    public ApiStatusException appendInnerStatusCode(StatusCode innerStatusCode) {
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
