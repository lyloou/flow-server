package com.lyloou.flow.service;

import com.google.common.base.Strings;
import com.lyloou.common.exception.DbException;
import com.lyloou.common.exception.ParamException;
import com.lyloou.common.status.Result;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.common.status.StatusCodeDict;
import com.lyloou.flow.mapper.UserMapper;
import com.lyloou.flow.model.user.UserPassword;
import com.lyloou.flow.model.user.UserRegister;
import com.lyloou.flow.model.user.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
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

    @Autowired
    private ResultHandler resultHandler;

    public Result checkUserRegister(UserRegister userRegister) {
        if (Strings.isNullOrEmpty(userRegister.getName())) {
            return resultHandler.msgResult(() -> StatusCodeDict.PARAM_USER_NAME_PASSWORD_EMPTY);
        }
        if (Strings.isNullOrEmpty(userRegister.getPassword())) {
            return resultHandler.msgResult(() -> StatusCodeDict.PARAM_USER_NAME_PASSWORD_EMPTY);
        }

        UserPassword up = userMapper.getUserPasswordByName(userRegister.getName());
        if (up != null) {
            return resultHandler.msgResult(() -> StatusCodeDict.PARAM_USER_NAME_EXISTED);
        }
        return null;
    }

    @Transactional
    public void saveUser(@RequestBody UserRegister userRegister) {
        // 插入后，会回传 userID
        int ret = userMapper.insertUser(userRegister);
        if (ret < 1) {
            throw new DbException(String.format("insertUser|插入数据库出错：%s", userRegister));
        }
        ret = userMapper.insertUserPassword(userRegister);
        if (ret < 1) {
            throw new DbException(String.format("insertUserPassword|插入数据库出错：%s", userRegister));
        }
    }

    public void updateUser(Long userId, UserUpdate userUpdate) {
        userMapper.updateUser(userId, userUpdate);
    }
}
