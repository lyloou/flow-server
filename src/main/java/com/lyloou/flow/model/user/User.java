package com.lyloou.flow.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    private String name;

    private String email;

    private Long phone;

    private String avatar;

    private String personalSignature;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDisabled;
}