package com.lyloou.common.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

public class Result {

    @JsonProperty("err_code")
    private final int status;

    @JsonProperty("err_msg")
    private String msg;

    @JsonProperty("data")
    private Object data;

    public Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result appendMsg(String msg) {
        return appendMsg(msg, ", ");
    }

    public Result appendMsg(String msg, String sep) {
        if (!Strings.isNullOrEmpty(msg)) {
            this.msg = Joiner.on(sep).join(this.msg, msg);
        } else {
            this.msg = msg;
        }
        return this;
    }

}
