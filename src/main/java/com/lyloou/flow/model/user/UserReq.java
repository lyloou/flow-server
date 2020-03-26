package com.lyloou.flow.model.user;

import lombok.Builder;
import lombok.Data;


/**
 * @author lyloou
 */
@Data
@Builder
public class UserReq {
    private Long id;
    private String name;
    private String email;
    private Long phone;
    private String avatar;
    private String personalSignature;
}