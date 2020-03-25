package com.lyloou.common.status;


public interface ResultHandler {
    Result msgResult(StatusCode code, String detailMsg);

    Result msgResult(StatusCode code);

    /**
     * 正常返回时，才使用这个；
     * 错误的时候，不要用这个。填写data，有可能会导致解析不对；
     *
     * @param code 错误码
     * @param data 数据
     * @return 封闭的结果
     */
    Result dataResult(StatusCode code, Object data);

}
