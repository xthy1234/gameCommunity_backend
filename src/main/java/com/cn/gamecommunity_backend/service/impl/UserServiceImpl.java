package com.cn.gamecommunity_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.gamecommunity_backend.dto.LoginDTO;
import com.cn.gamecommunity_backend.dto.RegisterDTO;
import com.cn.gamecommunity_backend.entity.User;
import com.cn.gamecommunity_backend.mapper.UserMapper;
import com.cn.gamecommunity_backend.service.UserService;
import com.cn.gamecommunity_backend.utils.JwtUtil;
import com.cn.gamecommunity_backend.utils.PasswordUtil;
import com.cn.gamecommunity_backend.vo.LoginVO;
import com.cn.gamecommunity_backend.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginVO login(LoginDTO loginDTO) {
        User user = userMapper.findByAccount(loginDTO.getAccount());
        //验证用户是否存在
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        //验证密码是否正确
        if (!PasswordUtil.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        //检查用户状态
        if (user.getStatus() != 0) {
            throw new RuntimeException("用户状态异常");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getAccount(), loginDTO.getRememberMe() ? 7 : 1);
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUserInfo(convertToVO(user));
        return loginVO;

    }

    @Override
    @Transactional
    public void register(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new RuntimeException("两次输入的密码不一致");
        }
        //验证用户是否存在
        if (userMapper.countByAccount(registerDTO.getAccount()) > 0) {
            throw new RuntimeException("用户已存在");
        }
        //验证邮箱是否存在
        if (userMapper.countByEmail(registerDTO.getEmail()) > 0) {
            throw new RuntimeException("邮箱已被注册");
        }
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);
        //保存用户
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setName(user.getAccount());
        user.setSex("U");
        user.setStatus(0);
        user.setCreateUser(0L);
        user.setUpdateUser(0L);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setIsDeleted(false);

        user.setAvatar("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");

        userMapper.insert(user);
    }

    @Override
    public UserInfoVO getUserInfo(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return convertToVO(user);
    }

    public UserInfoVO getUserInfoByAccount(String account) {
        User user = userMapper.findByAccount(account);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return convertToVO(user);
    }

    @Override
    public void logout(String token) {
        // 实际项目中，可以将token加入黑名单或从Redis中删除
        // 这里简单实现，前端删除token即可

    }

    private UserInfoVO convertToVO(User user) {
        if (user == null) return null;

        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }
}
