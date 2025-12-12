package com.cn.gamecommunity_backend.controller;

import com.cn.gamecommunity_backend.entity.User1;
import com.cn.gamecommunity_backend.service.User1Service;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class User1Controller {
    @Autowired
    private User1Service user1Service;
@GetMapping("/user1")
    public String hello() {
        return "这是用户1的接口！";
    }
    @GetMapping("/user1s")
    public List<User1> getUser1() {
        return user1Service.getAllUser1s();

    }
}
