package com.wxuy.example.demo.completableFuture;


import javax.xml.crypto.Data;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureDemo3 {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		System.out.println("程序开始");
		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of the asynchronous computation";
		});
		TimeUnit.SECONDS.sleep(2);
		System.out.println("supplyAsync 定义完成");
		CompletableFuture<String> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("supplyAsync2 end");
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of the asynchronous computation";
		});
		TimeUnit.SECONDS.sleep(3);
		System.out.println("supplyAsync2 定义完成");

		System.out.println(supplyAsync.get(5, TimeUnit.SECONDS));
		System.out.println(supplyAsync2.get(5, TimeUnit.SECONDS));

	}

}
