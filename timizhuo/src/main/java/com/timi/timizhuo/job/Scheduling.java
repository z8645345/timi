package com.timi.timizhuo.job;

import com.timi.timizhuo.weibo.WeiboHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class Scheduling implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
	}
	
	@Autowired
	private WeiboHandle weiboHandle;
	
	@Scheduled(cron = "0 0 * * * ?")
	public void timiWeiboDataJob() {
		weiboHandle.timiWeiboDataHandle();
		System.out.println("同步完成");
	}

	@Scheduled(cron = "1/10 * * * * ?")
	public void timiWeiboCheckJob() {
		weiboHandle.timiWeiboCheckHandle();
	}
}
