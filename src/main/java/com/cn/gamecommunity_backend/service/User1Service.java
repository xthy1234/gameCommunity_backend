package com.cn.gamecommunity_backend.service;

import com.cn.gamecommunity_backend.entity.User1;
import com.cn.gamecommunity_backend.mapper.User1Mapper;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User1Service {
    private final User1Mapper user1Mapper;

    public User1Service(User1Mapper user1Mapper) {
        this.user1Mapper = user1Mapper;
    }

    public List<User1> getAllUser1s() {
        return user1Mapper.findAll();
    }
}
