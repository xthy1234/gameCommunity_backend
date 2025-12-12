package com.cn.gamecommunity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.gamecommunity.entity.User;
import com.cn.gamecommunity.mapper.UserMapper;
import com.cn.gamecommunity.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

        @Override
        public Page<User> getPage(int pageNum, int pageSize) {
                Page<User> page = new Page<>(pageNum, pageSize);
                return this.page(page);
        }

        @Override
        public Page<User> getPage(int pageNum, int pageSize, String name, String account) {
                Page<User> page = new Page<>(pageNum, pageSize);
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();

                if (name != null && !name.isEmpty()) {
                        queryWrapper.like("name", name);
                }
                if (account != null && !account.isEmpty()) {
                        queryWrapper.like("account", account);
                }

                return this.page(page, queryWrapper);
        }

        @Override
        public Long login(String account, String password) {
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("account", account).eq("password", password);
                User user = this.getOne(queryWrapper);
                return user != null ? user.getId() : null;
        }
}
