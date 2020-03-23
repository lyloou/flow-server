package com.lyloou.flow.service;

import com.lyloou.common.exception.ParamException;
import com.lyloou.flow.mapper.UserMapper;
import com.lyloou.flow.model.user.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    @Autowired
    UserMapper userMapper;

    public void validate(String authorization, Long userId) {
        UserPassword userPassword = userMapper.getUserPasswordByUserId(userId);
        if (userPassword == null) {
            throw new ParamException(String.format("用户ID:%s不存在", userId));
        }
        if (!userPassword.getPassword().equals(authorization)) {
            throw new ParamException("用户密码不正确");
        }
    }
}
