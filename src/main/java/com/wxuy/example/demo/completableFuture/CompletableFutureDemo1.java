package com.wxuy.example.demo.completableFuture;


import io.netty.util.concurrent.CompleteFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo1 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<String> stringCompletableFuture = new CompletableFuture<>();
		stringCompletableFuture.complete("完成");
		String s = stringCompletableFuture.get();
		System.out.println(s);

	}

}
