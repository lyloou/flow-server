package com.lyloou.flow.model.user;

import lombok.Builder;
import lombok.Data;


/**
 * @author lyloou
 */
@Data
@Builder
public class UserUpdate {
    private String nickname;
    private String email;
    private Long phone;
    private String avatar;
    private String personalSignature;
}