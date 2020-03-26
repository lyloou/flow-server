package com.lyloou.common.status;


/**
 * 处理 Result 的接口，具体实现看 ResultHandlerImpl
 */
public interface ResultHandler {

    /**
     * 只需要返回 code 和 msg
     *
     * @param code 状态码
     * @return 结果
     */
    Result msgResult(StatusCode code);

    /**
     * 正常返回时，才使用这个；
     * 错误的时候，尽量不要用这个，除非前端有作处理。填写不当的 data，有可能会导致解析不对；
     *
     * @param code 错误码
     * @param data 返回给调用者的数据
     * @return 结果
     */
    Result dataResult(StatusCode code, Object data);

}
