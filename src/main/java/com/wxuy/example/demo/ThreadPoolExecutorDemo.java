package com.wxuy.example.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
/**
 * CPU 密集型任务(N+1)： 这种任务消耗的主要是 CPU 资源，可以将线程数设置为 N（CPU 核心数）+1，比 CPU 核心数多出来的一个线程是为了防止线程偶发的缺页中断，或者其它原因导致的任务暂停而带来的影响。一旦任务暂停，CPU 就会处于空闲状态，而在这种情况下多出来的一个线程就可以充分利用 CPU 的空闲时间。
 * I/O 密集型任务(2N)： 这种任务应用起来，系统会用大部分的时间来处理 I/O 交互，而线程在处理 I/O 的时间段内不会占用 CPU 来处理，这时就可以将 CPU 交出给其它线程使用。因此在 I/O 密集型任务的应用中，我们可以多配置一些线程，具体的计算方法是 2N。
 * 如何判断是 CPU 密集任务还是 IO 密集任务？
 *
 * CPU 密集型简单理解就是利用 CPU 计算能力的任务比如你在内存中对大量数据进行排序。但凡涉及到网络读取，文件读取这类都是 IO 密集型，这类任务的特点是 CPU 计算耗费时间相比于等待 IO 操作完成的时间来说很少，大部分时间都花在了等待 IO 操作完成上。
 */
public class ThreadPoolExecutorDemo {
	private static final int CORE_POOL_SIZE = 5;
	private static final int MAX_POOL_SIZE = 10;
	private static final int QUEUE_CAPACITY = 100;
	private static final Long KEEP_ALIVE_TIME = 1L;
	private static final String threadNamePrefix = "自定义线程名";

	public static void main(String[] args) {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat(threadNamePrefix + "-%d")
				.setDaemon(true).build();
		//使用阿里巴巴推荐的创建线程池的方式
		//通过ThreadPoolExecutor构造函数自定义参数创建
/*		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				CORE_POOL_SIZE,
				MAX_POOL_SIZE,
				KEEP_ALIVE_TIME,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(QUEUE_CAPACITY),
				new ThreadPoolExecutor.CallerRunsPolicy());*/
		//自定义线程名
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				CORE_POOL_SIZE,
				MAX_POOL_SIZE,
				KEEP_ALIVE_TIME,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(QUEUE_CAPACITY),
				threadFactory);
		List<Future<String>> futureList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			executor.execute(() -> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("CurrentThread name:" + Thread.currentThread().getName() + "date：" + Instant.now());
			});
/*			Future<String> future = executor.submit(() -> Thread.currentThread().getName());
			futureList.add(future);*/
			/**
			 * execute()方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功与否；
			 * submit()方法用于提交需要返回值的任务。线程池会返回一个 Future 类型的对象，通过这个 Future 对象可以判断任务是否执行成功 ，并且可以通过 Future 的 get()方法来获取返回值，get()方法会阻塞当前线程直到任务完成，而使用 get（long timeout，TimeUnit unit）方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完。
			 */
		}
		//future 的get方法会堵塞主线程
/*		for (Future<String> stringFuture : futureList) {
			try {
				System.out.println(new Date() + "::" + stringFuture.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}*/
		//终止线程池
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished all threads");
	}

	/**
	 * 打印线程池的状态
	 *
	 * @param threadPool 线程池对象
	 */
/*	public static void printThreadPoolStatus(ThreadPoolExecutor threadPool) {
		ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, createThreadFactory("print-images/thread-pool-status", false));
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			log.info("=========================");
			log.info("ThreadPool Size: [{}]", threadPool.getPoolSize());
			log.info("Active Threads: {}", threadPool.getActiveCount());
			log.info("Number of Tasks : {}", threadPool.getCompletedTaskCount());
			log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
			log.info("=========================");
		}, 0, 1, TimeUnit.SECONDS);
	}*/
	/**
	 * 创建一个可重用固定数量线程的线程池
	 */
	public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
		return new ThreadPoolExecutor(nThreads, nThreads,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(),
				threadFactory);
	}

}
