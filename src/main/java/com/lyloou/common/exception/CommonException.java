package com.lyloou.common.exception;

public class CommonException extends RuntimeException {


    public CommonException() {
        super();
    }

    public CommonException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CommonException(String s) {
        super(s);
    }
}
