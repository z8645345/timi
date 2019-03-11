package com.timi.timizhuo.job;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.timi.timizhuo.weibo.WeiboHandle;

@Configuration
@EnableScheduling
public class Scheduling implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
	}
	
	@Autowired
	private WeiboHandle weiboHandle;
	
	@Scheduled(cron = "0 0/5 * * * ?")
	public void timiWeiboDataJob() {
		weiboHandle.timiWeiboDataHandle();
		System.out.println("同步完成");
	}

}
