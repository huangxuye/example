package com.wxuy.example.demo.completableFuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo8 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		/**
		 * 使用thenCombine()组合两个独立的 future
		 * 虽然thenCompose()被用于当一个future依赖另外一个future的时候用来组合两个future。thenCombine()被用来当两个独立的Future都完成的时候，用来做一些事情。
		 */
		CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return 65.0;
		});
		CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return 177.8;
		});

		System.out.println(weightInKgFuture.thenCombine(heightInCmFuture, (aDouble, aDouble2) -> aDouble * aDouble2).get());

	}


}
