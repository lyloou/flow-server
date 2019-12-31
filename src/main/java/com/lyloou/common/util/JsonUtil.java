package com.lyloou.common.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;

/**
 * https://github.com/alibaba/sca-best-practice/blob/master/codeless-framework/codeless-core/src/main/java/com/alibaba/codeless/framework/core/utils/JsonUtils.java
 */
public interface JsonUtil {
    String FORMAT = "yyyy-MM-dd HH:mm:ss";
    Gson GSON = new GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(FORMAT)
            .create();

    static String toJson(Object object) {
        return GSON.toJson(object);
    }

    static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return GSON.fromJson(jsonString, clazz);
    }

    static <T> T fromJson(String jsonString, Type type) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        //noinspection unchecked
        return (T) GSON.fromJson(jsonString, type);
    }

}