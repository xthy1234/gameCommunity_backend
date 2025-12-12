package com.cn.gamecommunity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameCommunityApplicationTests {


	public static void main(String[] args) {
		SpringApplication.run(GameCommunityApplicationTests.class, args);
		System.out.println("✅ 后端启动成功！访问：http://localhost:8080");
	}

}
