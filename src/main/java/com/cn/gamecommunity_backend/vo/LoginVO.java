package com.cn.gamecommunity_backend.vo;


import lombok.Data;


@Data
public class LoginVO {
    //JWT令牌
    private String token;
    //用户信息
    private UserInfoVO userInfo;
    //过期时间（秒）
    private long empireTime;


}
