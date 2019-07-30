package com.lyloou.common.status;


public class ResultHandler {

    private ApiStatusProps apiStatusProps;

    public ResultHandler(ApiStatusProps apiStatusProps) {
        this.apiStatusProps = apiStatusProps;
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
