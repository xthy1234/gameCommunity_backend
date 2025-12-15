package com.cn.gamecommunity_backend.service;

import com.cn.gamecommunity_backend.dto.LoginDTO;
import com.cn.gamecommunity_backend.dto.RegisterDTO;
import com.cn.gamecommunity_backend.vo.LoginVO;
import com.cn.gamecommunity_backend.vo.UserInfoVO;

public interface UserService {
    //    Page<User> getPage(int pageNum, int pageSize);
//    Page<User> getPage(int pageNum, int pageSize, String name, String account);
//    Long login(String account, String password);
    LoginVO login(LoginDTO loginDTO);

    void register(RegisterDTO registerDTO);

    UserInfoVO getUserInfo(Long id);

    UserInfoVO getUserInfoByAccount(String account);

    void logout(String token);
}
