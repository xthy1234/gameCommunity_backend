package com.cn.gamecommunity_backend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameCommunityController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World! 这是我的第一个接口！";
    }
    @GetMapping("/help")
    public String help() {
        return "这是帮助页面";
    }
}