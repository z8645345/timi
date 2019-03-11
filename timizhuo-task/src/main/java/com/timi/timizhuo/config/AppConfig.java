package com.timi.timizhuo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class AppConfig {
	
	@Autowired
	private Environment environment;

	/**
	 * 数据库连接池
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DruidDataSource ds = new DruidDataSource();
		// 用户名
		ds.setUsername(environment.getProperty("spring.datasource.username"));
		// 密码
		ds.setPassword(environment.getProperty("spring.datasource.password"));
		// 数据库连接地址
		ds.setUrl(environment.getProperty("spring.datasource.url"));
		// 连接驱动
		ds.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
		// 初始化连接数
		ds.setInitialSize(Integer.parseInt(environment.getProperty("spring.datasource.initialSize")));
		// 最大连接数
		ds.setMaxActive(Integer.parseInt(environment.getProperty("spring.datasource.maxActive")));
		// 最大等待时间
		ds.setMaxWait(Long.parseLong(environment.getProperty("spring.datasource.maxWaitMillis")));
		// 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
		ds.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("spring.datasource.testWhileIdle")));
		return ds;
	}
	
}
