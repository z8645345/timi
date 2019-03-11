package com.timi.timizhuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource("classpath:config/application.properties")
@PropertySource("classpath:config/mybatis.properties")
@PropertySource("classpath:config/datasource.properties")
@MapperScan("com.timi.timizhuo.dao.mapper")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
}