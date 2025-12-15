package com.cn.gamecommunity_backend.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@TableName("users")
/**
 * 用户信息
 */
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @TableId(type = IdType.AUTO)
    private Long id;


    //昵称
    private String name;


    //账号
    private String account;

    //密码
    private String password;

    //性别 F 女 M 男 U未知
    private char sex;

    //手机号
    private String phone;

    //邮箱
    private String email;

    //生日
    private LocalDate birthday;

    //个性签名
    private String tag;

    //头像
    private String avatar;

    //状态 0:正常，1:禁用
    private Integer status;

    //是否删除
    private Boolean isDeleted;

    @TableField(fill = FieldFill.INSERT) //插入时填充字段
    private ZonedDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
    private ZonedDateTime updateTime;

    @TableField(fill = FieldFill.INSERT) //插入时填充字段
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
    private Long updateUser;

}
