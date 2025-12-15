package com.cn.gamecommunity_backend.vo;


import lombok.Data;


@Data
public class LoginVO {
    private String token;  //JWT令牌
    private UserInfoVO userInfo; //用户信息
    private long empireTime;

}
