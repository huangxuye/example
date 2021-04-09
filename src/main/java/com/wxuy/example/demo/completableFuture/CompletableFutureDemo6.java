package com.wxuy.example.demo.completableFuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo6 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		/**
		 * Supplier函数传入thenApply将返回一个简单的值，但是在本例中，将返回一个CompletableFuture。以上示例的最终结果是一个嵌套的CompletableFuture。
		 * 如果你想获取最终的结果给最顶层future，使用 thenCompose()方法代替
		 */
		CompletableFuture<CompletableFuture<String>> thenCompose1 = getThenCompose1("thenCompose1").thenApply(s -> getThenCompose2(s));
		System.out.println(thenCompose1.get().get());

		CompletableFuture<String> thenCompose11 = getThenCompose1("").thenCompose(s -> getThenCompose2(s));
		System.out.println(thenCompose11.get());

	}
	static CompletableFuture<String> getThenCompose1(String s) {
		return CompletableFuture.supplyAsync(() -> s);
	}

	static CompletableFuture<String> getThenCompose2(String s) {
		return CompletableFuture.supplyAsync(() -> {
			if("thenCompose1".equals(s)){
				return "thenCompose2";
			}else {
				return "error";
			}
		});
	}
}
