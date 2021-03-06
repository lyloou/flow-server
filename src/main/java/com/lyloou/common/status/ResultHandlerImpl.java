package com.lyloou.common.status;


import org.springframework.stereotype.Component;

@Component
public class ResultHandlerImpl implements ResultHandler {

    @Override
    public Result msgResult(StatusCode code) {
        StatusCodeDict status = code.get();
        return new Result(status.code(), status.msg());
    }


    @Override
    public Result dataResult(StatusCode code, Object data) {
        StatusCodeDict status = code.get();
        Result result = new Result(status.code(), status.msg());
        result.data(data);
        return result;
    }

}
