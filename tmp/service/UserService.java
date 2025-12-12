package com.cn.gamecommunity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.gamecommunity.entity.User;

public interface UserService {
    Page<User> getPage(int pageNum, int pageSize);
    Page<User> getPage(int pageNum, int pageSize, String name, String account);
    Long login(String account, String password);
}
