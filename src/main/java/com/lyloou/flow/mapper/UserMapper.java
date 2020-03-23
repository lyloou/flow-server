package com.lyloou.flow.mapper;


import com.lyloou.flow.model.user.User;
import com.lyloou.flow.model.user.UserPassword;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user_password where user_id=#{userId}")
    UserPassword getUserPasswordByUserId(Long userId);

    @Select("select * from user_password where name=#{name} and password=#{password}")
    UserPassword getUserPasswordByNamePassword(String name, String password);

    @Select("select * from user where id=#{userId}")
    User getUser(Long userId);
}
