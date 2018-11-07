package com.lyloou.common.status.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.util.List;

public class Result {

    @JsonProperty("err_code")
    private final int status;

    @JsonProperty("err_msg")
    private String msg;

    private List<Result> innerStatus;

    private Object data;

    public Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result appendMsg(String msg, String sep) {
        if (!Strings.isNullOrEmpty(msg)) {
            this.msg = Joiner.on(sep).join(this.msg, msg);
        } else {
            this.msg = msg;
        }
        return this;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setInnerStatus(List<Result> innerStatus) {
        this.innerStatus = innerStatus;
    }

    public List<Result> getInnerStatus() {
        return innerStatus;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return data;
    }
}
