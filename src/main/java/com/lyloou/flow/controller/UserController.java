package com.lyloou.flow.controller;

import com.lyloou.common.status.Result;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.flow.mapper.UserMapper;
import com.lyloou.flow.model.user.User;
import com.lyloou.flow.model.user.UserPassword;
import com.lyloou.flow.model.user.UserReq;
import com.lyloou.flow.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.lyloou.common.status.StatusCodeDict.COMMON_OK;
import static com.lyloou.common.status.StatusCodeDict.PARAM_LOGIN_ERROR;

@RestController
@RequestMapping(path = "${apiVersion}/user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private ResultHandler resultHandler;


    @Autowired
    Validator validator;

    @PostMapping("login")
    public Result login(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        UserPassword userPassword = userMapper.getUserPasswordByNamePassword(name, password);
        if (userPassword == null) {
            return resultHandler.dataResult(() -> PARAM_LOGIN_ERROR, null);
        }
        User user = userMapper.getUser(userPassword.getUserId());
        return resultHandler.dataResult(() -> COMMON_OK, user);
    }


    @PostMapping("/update")
    public Result update(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("UserId") Long userId,
            @RequestBody UserReq userReq) {
        validator.validate(authorization, userId);
        return resultHandler.msgResult(() -> COMMON_OK);
    }
}
