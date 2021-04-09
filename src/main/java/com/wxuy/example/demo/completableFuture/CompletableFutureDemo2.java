package com.wxuy.example.demo.completableFuture;


import io.netty.util.Timeout;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo2 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			System.out.println("I'll run in a separate thread than the main thread.");
		});
		voidCompletableFuture.get();
	}

}
