package com.lyloou.common.http;


import com.lyloou.common.exception.CommonException;

public class CommonHttpRespEmptyException extends CommonException {
    public CommonHttpRespEmptyException() {
        super();
    }

    public CommonHttpRespEmptyException(String s) {
        super(s);
    }
}
