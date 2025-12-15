package com.cn.gamecommunity_backend.controller;

import com.cn.gamecommunity_backend.dto.LoginDTO;
import com.cn.gamecommunity_backend.dto.RegisterDTO;
import com.cn.gamecommunity_backend.service.UserService;
import com.cn.gamecommunity_backend.utils.JwtUtil;
import com.cn.gamecommunity_backend.vo.LoginVO;
import com.cn.gamecommunity_backend.vo.Result;
import com.cn.gamecommunity_backend.vo.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController    //restful风格
@RequestMapping("/api/auth")   //接口前缀
public class AuthController {
    //用户服务层
    private final UserService userService;
    //JWT工具类
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 登录
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@Validated @RequestBody LoginDTO loginDTO) {
        try {
            // 调用service层登录方法进行登录验证
            LoginVO loginVO = userService.login(loginDTO);
            // 生成JWT令牌（有效7天）
            String token = jwtUtil.generateToken(loginVO.getUserInfo().getId(), loginVO.getUserInfo().getAccount(), 7);
            // 设置令牌
            loginVO.setToken(token);
            // 返回成功结果
            return Result.success(loginVO);
        } catch (Exception e) {
            // 登录失败
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
            // 调用service层注册方法进行注册
            userService.register(registerDTO);
            // 返回成功结果
            return Result.success();
        } catch (Exception e) {
            // 注册失败
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
            if (token == null || !jwtUtil.validateToken(token)) {
                return Result.error(401, "请先登录");
            }
            //从token中获取账号信息
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
            if (token == null || !jwtUtil.validateToken(token)) {
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
