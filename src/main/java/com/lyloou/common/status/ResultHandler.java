package com.lyloou.common.status;


import com.lyloou.common.status.resp.Result;

public class ResultHandler {

    private ApiStatusProps apiStatusProps;

    void setApiStatusProps(ApiStatusProps apiStatusProps) {
        this.apiStatusProps = apiStatusProps;
    }

    public Result newResult(int code, String msg){
        return new Result(code, msg);
    }

    public Result msgResult(StatusCode code) {
        String msg = apiStatusProps.message(code);
        return new Result(code.get(), msg);
    }


    public Result dataResult(StatusCode code, Object data) {
        String msg = apiStatusProps.message(code);
        Result result = new Result(code.get(), msg);
        result.data(data);
        return result;
    }

}
