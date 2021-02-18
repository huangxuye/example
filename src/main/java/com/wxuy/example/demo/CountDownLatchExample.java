package com.wxuy.example.demo;

import java.util.concurrent.*;

/**
 * @Description: CountDownLatch 使用方法示例
 */
public class CountDownLatchExample {
	// 请求的数量
	private static final int threadCount = 550;

	public static void main(String[] args) throws InterruptedException {
		// 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
//		ExecutorService threadPool = Executors.newFixedThreadPool(300);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(300, 300,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			final int threadnum = i;
			executor.execute(() -> {// Lambda 表达式的运用
				try {
					test(threadnum);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();// 表示一个请求已经被完成
				}

			});
		}
		countDownLatch.await();
		executor.shutdown();
		System.out.println("finish");
	}

	public static void test(int threadnum) throws InterruptedException {
		Thread.sleep(1000);// 模拟请求的耗时操作
		System.out.println("threadnum:" + threadnum);
		Thread.sleep(1000);// 模拟请求的耗时操作
	}
}