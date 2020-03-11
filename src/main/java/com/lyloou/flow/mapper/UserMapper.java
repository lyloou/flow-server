package com.lyloou.flow.mapper;


import com.lyloou.flow.model.flow.UserPassword;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user_password where user_id=#{userId}")
    UserPassword getUserPassword(Long userId);
}
