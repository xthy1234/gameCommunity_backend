package com.cn.gamecommunity_backend.controller;

import com.cn.gamecommunity_backend.entity.User1;
import com.cn.gamecommunity_backend.service.User1Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class User1Controller {
    private final User1Service user1Service;

    public User1Controller(User1Service user1Service) {
        this.user1Service = user1Service;
    }

    @GetMapping("/user1")
    public String hello() {
        return "这是用户1的接口！";
    }

    @GetMapping("/user1s")
    public List<User1> getUser1() {
        return user1Service.getAllUser1s();

    }
}
