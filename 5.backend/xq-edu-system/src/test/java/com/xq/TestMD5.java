package com.xq;

import com.xq.utils.MD5Utils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 */
public class TestMD5 {

    // 使用MD5算法加密 不管加密多少次，密文密码都是一样的，可以通过穷举法暴力快速破解
    @Test
    public void testMD5(){
        String pwd1 = MD5Utils.MD5EncodeUtf8("12345"); // 827CCB0EEA8A706C4C34A16891F84E7B
        System.out.println(pwd1);
        String pwd2 = MD5Utils.MD5EncodeUtf8("12345"); // 827CCB0EEA8A706C4C34A16891F84E7B
        System.out.println(pwd2);
    }

    @Test
    public void testBcrypt(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd1 = encoder.encode("12345");
        String pwd2 = encoder.encode("12345");
        String pwd3 = encoder.encode("12345");
        System.out.println(pwd1); // $2a$10$KzaJDjSKAQQYVqz2rLiL4.ba1uEJD0RqtQdp2rRlNRMasEpkkSUI.
        System.out.println(pwd2); // $2a$10$IH6M8y7Vhf141Vpo5Wrr1ObWY3X41JJQMvZBlDeRdxNOaL5iFcJSu
        System.out.println(pwd3); // $2a$10$MQOouWLpykyA9DVgRKkXGeJzMjfFMEaWcrcMPyBZcXnsEvlljP.bu

        boolean result1 = encoder.matches("12345", "$2a$10$KzaJDjSKAQQYVqz2rLiL4.ba1uEJD0RqtQdp2rRlNRMasEpkkSUI.");
        boolean result2 = encoder.matches("12345", "$2a$10$IH6M8y7Vhf141Vpo5Wrr1ObWY3X41JJQMvZBlDeRdxNOaL5iFcJSu");
        boolean result3 = encoder.matches("12345", "$2a$10$MQOouWLpykyA9DVgRKkXGeJzMjfFMEaWcrcMPyBZcXnsEvlljP.bu");
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
