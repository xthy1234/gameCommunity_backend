package com.cn.gamecommunity_backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码工具类，用于处理密码加密和解密
 */

public class PasswordUtil {
    //创建静态的BCryptPasswordEncoder实例，单例模式
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encode(String password) {
        return encoder.encode(password);
    }

    /**
     * 验证密码
     *
     * @param password          密码
     * @param encodedPassword 数据库中加密后的密码
     * @return 是否匹配
     */
    public static boolean matches(String password, String encodedPassword) {
        return encoder.matches(password, encodedPassword);
    }
}
