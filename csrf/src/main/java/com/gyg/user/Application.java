package com.gyg.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 郭永钢
 */
@SpringBootApplication
@MapperScan("com.gyg.user.mapper")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
