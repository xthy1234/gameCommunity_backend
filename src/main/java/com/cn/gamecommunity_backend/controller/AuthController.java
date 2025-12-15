package com.cn.gamecommunity_backend.controller;

import com.cn.gamecommunity_backend.dto.LoginDTO;
import com.cn.gamecommunity_backend.dto.RegisterDTO;
import com.cn.gamecommunity_backend.service.UserService;
import com.cn.gamecommunity_backend.utils.JwtUtil;
import com.cn.gamecommunity_backend.vo.LoginVO;
import com.cn.gamecommunity_backend.vo.Result;
import com.cn.gamecommunity_backend.vo.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@Validated @RequestBody LoginDTO loginDTO) {
        try {
            LoginVO loginVO = userService.login(loginDTO);
            String token = jwtUtil.generateToken(loginVO.getUserInfo().getId(), loginVO.getUserInfo().getAccount(), 7);
            loginVO.setToken(token);
            return Result.success(loginVO);
        } catch (Exception e) {
            return Result.error(e.getMessage() + "用户名或密码错误");
        }
    }

    /**
     * 注册
     * POST /api/auth/register
     */
    @PostMapping("/register")
    public Result<Void> register(@Validated @RequestBody RegisterDTO registerDTO) {
        try {
            userService.register(registerDTO);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 获取用户信息
     * GET /api/auth/info
     */
    @PostMapping("/info")
    public Result<UserInfoVO> getUserInfo(HttpServletRequest request) {
        try {
            //从请求头中获取token
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            if (token != null || !jwtUtil.validateToken(token)) {
                return Result.error(401, "请先登录");
            }
            String account = jwtUtil.getAccountFromToken(token);
            UserInfoVO userInfo = userService.getUserInfoByAccount(account);
            return Result.success(userInfo);
        } catch (Exception e) {
            return Result.error(401, "获取用户失败");
        }
    }

    /**
     * 登出
     * POST /api/auth/logout
     */
    //ai生成的
    @PostMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        try {
            //从请求头中获取token
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            if (token != null || !jwtUtil.validateToken(token)) {
                return Result.error(401, "请先登录");
            }
            String account = jwtUtil.getAccountFromToken(token);
            userService.logout(token);
            return Result.success();
        } catch (Exception e) {
            return Result.error(401, "登出失败");
        }
    }
}
