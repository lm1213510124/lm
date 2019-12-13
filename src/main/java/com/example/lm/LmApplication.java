package com.example.lm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.lm.mapper")//启动类增加mapper扫描
public class LmApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmApplication.class, args);
	}

}
