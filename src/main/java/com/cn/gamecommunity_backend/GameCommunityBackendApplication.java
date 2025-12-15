package com.cn.gamecommunity_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cn.gamecommunity_backend.mapper")
public class GameCommunityBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameCommunityBackendApplication.class, args);
        System.out.println("✅ 后端启动成功！访问：http://localhost:8080");
    }
}
