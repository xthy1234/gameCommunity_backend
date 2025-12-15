package com.cn.gamecommunity_backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//JWT工具类，处理身份认证和授权

@Component
public class JwtUtil {
    /**
     * 从配置中读取密钥
     */
    @Value("${jwt.secret:mySecretKey}")
    private String secret;

    /**
     * 从配置中读取过期时间（秒）
     */
    @Value("${jwt.expiration:86400}")
    private Long expiration;
    /**
     * 生成 JWT token,接收用户 ID、用户名和有效期天数，创建一个包含这些信息的 Token
     *
     * @param userId   用户ID
     * @param account  账号
     * @param days     过期时间（天）
     * @return JWT
     */

    public String generateToken(Long userId, String account, Integer days) {
        //使用密钥创建签名key
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        //创建载荷（claim），用来存储用户信息
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);//用户ID
        claims.put("account", account);//账号
        claims.put("created", new Date());//创建时间

        //创建JWT，并返回
        return Jwts.builder()
                .claims(claims)  //添加载荷
                .subject(account)  //添加主题（通常是用户名，这里是账号。。可能要改）
                .issuedAt(new Date())  //签发时间
                .expiration(new Date(System.currentTimeMillis() + (days * 24 * 3600 * 1000L))) //过期时间
                .signWith(key, Jwts.SIG.HS512)  //使用HS512签名算法对JWT进行签名
                .compact();  //生成字符串

    }

    /**
     * 验证JWT token，检查是否过期
     *
     * @param token JWT
     * @return 是否过期
     */

    public boolean validateToken(String token) {
        try {
            getClaimsFromToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }




    /**
     * 从token中获取账号
     * @param token
     * @return
     */
    public String getAccountFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     * 从token中获取用户ID
     * @param token
     * @return
     */

    public Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("userId", Long.class);
    }

    /**
     * 从token中获取载荷（claims）
     * @param token
     * @return
     */

    private Claims getClaimsFromToken(String token) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            return Jwts.parser()
                    .verifyWith(secretKey) //使用相同的密钥验证token
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();  //获取载荷（claims）
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
