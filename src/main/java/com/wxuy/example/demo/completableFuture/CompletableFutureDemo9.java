package com.wxuy.example.demo.completableFuture;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureDemo9 {
	private static final int CORE_POOL_SIZE = 9;
	private static final int MAX_POOL_SIZE = 18;
	private static final int QUEUE_CAPACITY = 100;
	private static final Long KEEP_ALIVE_TIME = 1L;

	private static final String threadNamePrefix = "one_click_diagnosis_thread_pool";
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
		//log.info("{} 线程池开启",threadNamePrefix);
		/**
		 * 我们使用thenCompose()和 thenCombine()把两个CompletableFuture组合在一起。现在如果你想组合任意数量的CompletableFuture，应该怎么做？我们可以使用以下两个方法组合任意数量的CompletableFuture。
		 *static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
		 * static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
		 */
		List<String> webPageLinks = Arrays.asList("1","2","3","4","5","5","6","6","6");
		/**
		 * 现在，当所有的页面已经下载完毕，你想计算包含关键字CompletableFuture页面的数量。可以使用CompletableFuture.allOf()达成目的。
		 */
		List<CompletableFuture<String>> pageContentFutures  = webPageLinks.stream().map(s -> downloadWebPage(s)).collect(Collectors.toList());
		/**
		 * 使用CompletableFuture.allOf()的问题是它返回CompletableFuture。但是我们可以通过写一些额外的代码来获取所有封装的CompletableFuture结果。
		 */
		CompletableFuture<Void> allFutures  = CompletableFuture.allOf(pageContentFutures .toArray(new CompletableFuture[pageContentFutures .size()]));

		/**
		 * 花一些时间理解下以上代码片段。当所有future完成的时候，我们调用了future.join()，因此我们不会在任何地方阻塞。
		 * join()方法和get()方法非常类似，这唯一不同的地方是如果最顶层的CompletableFuture完成的时候发生了异常，它会抛出一个未经检查的异常。
		 */

		try {
			//执行多线程处理 最多等待120s
			allFutures.get(120, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			//throw new Exception("fileProvenanceDealFutures处理超时异常");
		}finally {
			//关闭线程池
			executor.shutdown();
			try {
				if(!executor.awaitTermination(5, TimeUnit.SECONDS)){
					executor.shutdownNow();
				}
				//log.info("{} 线程池已关闭",threadNamePrefix);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(aVoid -> {
			return pageContentFutures.stream()
					.map(pageContentFuture -> pageContentFuture.join())
					.collect(Collectors.toList());
		});
		CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> {
			return pageContents.stream()
					.filter(pageContent -> pageContent.contains("66"))
					.count();
		});
		System.out.println(countFuture.get());
		CompletableFuture<Long> objectCompletableFuture = allFutures.thenApply(aVoid -> {
			return pageContentFutures.stream()
					.map(pageContentFuture -> pageContentFuture.join())
					.collect(Collectors.toList());
		}).thenApply(strings -> {
			return strings.stream()
					.filter(pageContent -> pageContent.contains("66"))
					.count();
		});
		System.out.println(objectCompletableFuture.get());
	}
	static CompletableFuture<String> downloadWebPage(String pageLink) {
		return CompletableFuture.supplyAsync(() -> {
			return pageLink+pageLink;
		});
	}

}
