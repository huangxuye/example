package com.wxuy.example.demo.completableFuture;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureDemo11 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		/**
		 * CompletableFuture.supplyAsync(() -> {
		 * 	// Code which might throw an exception
		 * 	return "Some result";
		 * }).thenApply(result -> {
		 * 	return "processed result";
		 * }).thenApply(result -> {
		 * 	return "result after further processing";
		 * }).thenAccept(result -> {
		 * 	// do something with the final result
		 * });
		 如果在原始的supplyAsync()任务中发生一个错误，这时候没有任何thenApply会被调用并且future将以一个异常结束。如果在第一个thenApply发生错误，这时候第二个和第三个将不会被调用，同样的，future将以异常结束。
		 1. 使用 exceptionally() 回调处理异常
		 exceptionally()回调给你一个从原始Future中生成的错误恢复的机会。你可以在这里记录这个异常并返回一个默认值。
		 */
		Integer age = -1;

		CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
			if(age < 0) {
				throw new IllegalArgumentException("Age can not be negative");
			}
			if(age > 18) {
				return "Adult";
			} else {
				return "Child";
			}
		}).exceptionally(ex -> {
			System.out.println("Oops! We have an exception - " + ex.getMessage());
			return "Unknown!";
		});

		System.out.println("Maturity : " + maturityFuture.get());

		CompletableFuture<String> maturityFuture2 = CompletableFuture.supplyAsync(() -> {
			if(age < 0) {
				throw new IllegalArgumentException("Age can not be negative");
			}
			if(age > 18) {
				return "Adult";
			} else {
				return "Child";
			}
		}).handle((res, ex) -> {
			if(ex != null) {
				System.out.println("Oops! We have an exception - " + ex.getMessage());
				return "Unknown!";
			}
			return res;
		});

		System.out.println("Maturity : " + maturityFuture2.get());
	}

}
