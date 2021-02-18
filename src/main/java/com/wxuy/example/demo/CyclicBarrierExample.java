package com.wxuy.example.demo;

import java.util.concurrent.*;

/**
 * @Description: 测试 CyclicBarrier 类中带参数的 await() 方法
 */
public class CyclicBarrierExample {
	// 请求的数量
	private static final int threadCount = 550;
	// 需要同步的线程数量
	private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
	// 需要同步的线程数量

	/**
	 * CyclicBarrier 还提供一个更高级的构造函数CyclicBarrier(int parties, Runnable barrierAction)，用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景。示例代码如下：
	 */
	private static final CyclicBarrier cyclicBarrier2 = new CyclicBarrier(5, () -> {
		System.out.println("------当线程数达到之后，优先执行------");
	});
	public static void main(String[] args) throws InterruptedException {
		// 创建线程池
//		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			executor.execute(() -> {
				try {
					test(threadNum);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			});
		}
		executor.shutdown();
	}

	public static void test(int threadnum) throws InterruptedException, BrokenBarrierException {
		System.out.println("threadnum:" + threadnum + "is ready");
		try {
			/**等待60秒，保证子线程完全执行结束*/
			cyclicBarrier.await(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("-----CyclicBarrierException------");
		}
		System.out.println("threadnum:" + threadnum + "is finish");
	}

}
