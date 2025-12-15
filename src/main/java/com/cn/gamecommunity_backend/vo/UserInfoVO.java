package com.cn.gamecommunity_backend.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class UserInfoVO {
    private Long id;
    private String name;
    private String account;
    private char sex;
    private String phone;
    private String email;
    private LocalDate birthday;
    private String tag;
    private String avatar;
    private Integer status;
    private Boolean isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private ZonedDateTime createTime;
    private ZonedDateTime updateTime;
    private Long createUser;
    private Long updateUser;


}
