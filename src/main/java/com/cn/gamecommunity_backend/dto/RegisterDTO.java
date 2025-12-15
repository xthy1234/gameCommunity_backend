package com.cn.gamecommunity_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank(message = "账号不能为空")
    @Size(min = 4, message = "账号长度不能小于4")
    @Size(max = 20, message = "账号长度不能大于20")
    private String account;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, message = "密码长度不能小于8")
    @Size(max = 20, message = "密码长度不能大于20")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;


}
