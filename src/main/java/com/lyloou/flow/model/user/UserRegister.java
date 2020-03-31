package com.lyloou.flow.model.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegister {
    // 用户ID，插入成功后回调过来的，具体查看：UserMapper.insertUserPassword
    private Long id;

    private String name;
    private String password;
    private String email;
    private String avatar;
    private Long phone;
}