package com.wxuy.example.demo.completableFuture;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class CompletableFutureDemo4 {
	private static final int CORE_POOL_SIZE = 5;
	private static final int MAX_POOL_SIZE = 10;
	private static final int QUEUE_CAPACITY = 100;
	private static final Long KEEP_ALIVE_TIME = 1L;
	private static final String threadNamePrefix = "自定义线程名";
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat(threadNamePrefix + "-%d")
				.setDaemon(true).build();
		//自定义线程名
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				CORE_POOL_SIZE,
				MAX_POOL_SIZE,
				KEEP_ALIVE_TIME,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(QUEUE_CAPACITY),
				threadFactory);
		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of the asynchronous computation";
		},executor);
		String s = supplyAsync.get();
		System.out.println(s);

	}

}
