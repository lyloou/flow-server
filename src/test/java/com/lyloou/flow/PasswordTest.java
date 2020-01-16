package com.lyloou.flow;

import org.junit.Test;

import static com.lyloou.common.util.PasswordUtil.getEncodedPassword;

public class PasswordTest {
    @Test
    public void testDigest() {
        String originPassword = "123456";
        String encodedPassword = getEncodedPassword(originPassword);
        System.out.println(encodedPassword);
    }

}
