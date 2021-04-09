package com.wxuy.example.demo.completableFuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo3 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of the asynchronous computation";
		});
		String s = supplyAsync.get();
		System.out.println(s);

	}

}
