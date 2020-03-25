package com.lyloou.common.status;


import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class ResultHandlerImpl implements ResultHandler {

    @Override
    public Result msgResult(StatusCode code, String detailMsg) {
        StatusCodeDict status = code.get();
        if (Strings.isNullOrEmpty(detailMsg)) {
            new Result(status.code(), status.msg());
        }
        String m = status.msg() + "," + detailMsg;
        return new Result(status.code(), m);
    }

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
