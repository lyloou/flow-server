package com.lyloou.common.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 错误码字典
 */
public enum StatusCodeDict {


    // 通用码
    COMMON_OK(0, "ok"),
    COMMON_UNKNOWN(9999, "未知的异常"),
    COMMON_INVALID_REQUEST(9998, "无效的请求"),

    // 系统
    SYSTEM_404(404, "404你懂的"),
    SYSTEM_502(502, "服务器打洋了"),
    SYSTEM_500(500, "哎哟，这里有个锅"),

    PARAM(1001, "参数错误"),
    PARAM_BEYOND_QUANTITY_NUMBER(1003, "参数错误，超出最大数量限制"),

    // 登录
    PARAM_LOGIN_ERROR(1102, "用户名不存在或密码错误"),

    // 商品
    GOODS_IS_NOT_EXISTED(1301, "商品不存在"),
    GOODS_IS_NOT_MARKETABLE(1302, "商品已经下架"),

    DB(1401, "数据库异常"),

    UNDEFINED(1999, "未定义的业务异常"),
    ;


    private int code;
    private String msg;
    private static final Map<Integer, StatusCodeDict> dict = new HashMap<>();

    static {
        Arrays.stream(StatusCodeDict.values())
                .forEach(p -> dict.put(p.code, p));
    }

    public static StatusCodeDict of(int code) {
        return Optional.of(dict.get(code)).orElse(COMMON_UNKNOWN);
    }

    StatusCodeDict(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
