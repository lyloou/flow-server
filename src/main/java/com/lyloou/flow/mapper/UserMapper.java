package com.lyloou.flow.mapper;


import com.lyloou.flow.model.user.User;
import com.lyloou.flow.model.user.UserPassword;
import com.lyloou.flow.model.user.UserRegister;
import com.lyloou.flow.model.user.UserUpdate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select * from user_password where user_id=#{userId}")
    UserPassword getUserPasswordByUserId(Long userId);

    @Select("select * from user_password where name=#{name} and password=#{password}")
    UserPassword getUserPasswordByNamePassword(String name, String password);

    @Select("select * from user_password where name=#{name}")
    UserPassword getUserPasswordByName(String name);

    @Select("select * from user where id=#{userId}")
    User getUser(Long userId);

    // [intellij idea - Retrieve generated ID using MyBatis Annotation Spring Boot - Stack Overflow](https://stackoverflow.com/questions/59624465/retrieve-generated-id-using-mybatis-annotation-spring-boot)
    @Insert("insert into user (name,nickname,email,phone,avatar) " +
            " values (#{name},#{name},#{email},#{phone},#{avatar}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(UserRegister userRegister);

    @Insert("insert into user_password (user_id,name,password) " +
            " values (#{id},#{name},#{password})")
    int insertUserPassword(UserRegister userRegister);

    @Update("update user set nickname=#{uu.nickname}, " +
            "email=#{uu.email}, phone=#{uu.phone}, avatar=#{uu.avatar}, " +
            "personal_signature=#{uu.personalSignature} " +
            "where id=#{userId}")
    void updateUser(Long userId, UserUpdate uu);

}
