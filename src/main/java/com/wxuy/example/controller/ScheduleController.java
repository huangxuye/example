package com.wxuy.example.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

@Component
@EnableAsync
public class ScheduleController {

	@Async
	@Scheduled(cron = "5 * * * * ?")
	public void scheduleTest(){
		System.out.println("Test");
	}
	//定时任务需要配置@EnableScheduling 注解 然后在方法上对应加上  @Scheduled 才能启动
	//@Scheduled 注解启动的定时任务默认单线程 这里有两种方式启动多线程
	//一种是 添加 @EnableAsync（类上） 和 @Async （方法上） 启动多线程异步调用 每次都会创建新的线程。有 OOM 的风险
	//一种是 实现 SchedulingConfigurer 接口 重写 configureTasks 方法 将原先的
	// Executors.newSingleThreadScheduledExecutor();  修改为
	//scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
	//该方法会控制线程池中的线程。可以防止 OOM 但是任务可能会执行失败。

	//优化第二种 将线程池通过 @Bean 交给容器管理 不需要 实现 SchedulingConfigurer
//	@Bean
//	public TaskScheduler taskScheduler() {
//		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//		scheduler.setPoolSize(10);
//		return scheduler;
//	}
}
