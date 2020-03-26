package com.lyloou.common.status;

import java.util.function.Supplier;

/**
 * ResultHandle 用此类作为参数。
 * 通过此接口，可以通过 lambda 的方式来获取 StatusCodeDict 里的信息
 * (<code>resultHandler.msgResult(() -> PARAM_LOGIN_ERROR);</code>)
 */
@FunctionalInterface
public interface StatusCode extends Supplier<StatusCodeDict> {
    @Override
    StatusCodeDict get();
}
