package com.wxuy.example.demo.dataTimeApi;

import java.time.Duration;
import java.time.Instant;

public class InstantDemo1 {
	/**
	 * Instant实例表示时间线上的一个点。 参考点是标准的Java纪元(epoch)，即1970-01-01T00：00：00Z（1970年1月1日00:00 GMT）。
	 * Instant类的EPOCH属性返回表示Java纪元的Instant`实例。 在纪元之后的时间是正值，而在此之前的时间即是负值。
	 *
	 * Instant的静态now方法返回一个表示当前时间的Instant对象：
	 *
	 * Instant now = Instant.now();
	 * getEpochSecond方法返回自纪元以来经过的秒数。 getNano方法返回自上一秒开始以来的纳秒数。
	 * @param args
	 */
	public static void main(String[] args) {

		Instant start = Instant.now();

		// do something here

		Instant end = Instant.now();

		System.out.println(Duration.between(start, end).toMillis());

	}

}