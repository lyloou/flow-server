package com.lyloou.common.status;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "api.status")
public class ApiStatusProps {

    private Map<String, String> message;

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }

    public String message(StatusCode code) {
        return message.get(String.valueOf(code.get()));
    }

}
