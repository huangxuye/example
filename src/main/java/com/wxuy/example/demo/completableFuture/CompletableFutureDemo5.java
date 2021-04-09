package com.wxuy.example.demo.completableFuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo5 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		/**
		 * 使用 thenApply(), thenAccept() 和thenRun()方法附上一个回调给CompletableFuture。
		 *  thenApply() 可以使用 thenApply() 处理和改变CompletableFuture的结果。持有一个Function<R,T>作为参数。Function<R,T>是一个简单的函数式接口，接受一个T类型的参数，产出一个R类型的结果。
		 * 通过附加一系列的thenApply()在回调方法 在CompletableFuture写一个连续的转换。这样的话，结果中的一个 thenApply方法就会传递给该系列的另外一个 thenApply方法。
		 */
		CompletableFuture<String> thenApply = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "wxuy";
		}).thenApply(name -> {
			return "Hello " + name;
		}).thenApply(greeting -> {
			return greeting + ", Welcome to the Github";
		});

		CompletableFuture<String> thenApplyAgain = thenApply.thenApply(s -> s + ", thenApply again");

		System.out.println(thenApplyAgain.get());
		/**
		 * thenAccept() 和 thenRun()
		 * 如果你不想从你的回调函数中返回任何东西，仅仅想在Future完成后运行一些代码片段，你可以使用thenAccept()和 thenRun()方法，这些方法经常在调用链的最末端的最后一个回调函数中使用。
		 * CompletableFuture.thenAccept()持有一个Consumer<T>，返回一个CompletableFuture<Void>。它可以访问CompletableFuture的结果：
		 *
		 */
		thenApplyAgain.thenRun(() -> {
			System.out.println("输出日志");
		});

		thenApplyAgain.thenAccept(s -> {
			System.out.println("Got detail from thenApplyAgain: " + s);
		});



	}

}
