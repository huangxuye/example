package com.wxuy.example.demo.dataTimeApi;

import java.time.Duration;
import java.time.LocalDateTime;

public class DurationDemo1 {
	public static void main(String[] args) {
/*		可以通过调用静态方法between或of来创建Duration。
		下面的代码会在2015年1月26日11:10至2015年1月26日12:40之间创建两个LocalDateTime的Duration。*/
		LocalDateTime dateTimeA = LocalDateTime
				.of(2015, 1, 26, 8, 10, 0, 0);
		LocalDateTime dateTimeB = LocalDateTime
				.of(2015, 1, 26, 11, 40, 0, 0);
		Duration duration = Duration.between(
				dateTimeA, dateTimeB);

		System.out.printf("There are %d hours and %d minutes.%n",
				duration.toHours(),
				duration.toMinutes() % 60);
	}
	/*	Duration类是基于时间的持续时间的构建。 它与Period类似，
		不同之处在于Duration的时间分量为纳秒精度，并考虑了ZonedDateTime实例之间的时区。
		下表显示了Duration中重要的方法。

		方法	描述
		between	                        在两个时差的对象之间创建一个Duration实例，例如在两个LocalDateTime或两个ZonedDateTime之间。
		ofYears, ofMonths, ofWeeks, ofDays, ofHours,
		ofMinutes, ofSeconds, ofNano	创建给定年数/月/周/天/小时/分钟/秒/纳秒的Duration实例
		of	                            根据指定数量的时间单位创建Duration实例
		toDays, toHours, toMinutes	    以int形式返回此Duration的天数/小时/分钟数
		isNegative	                    如果此Duration为负，则返回true。 否则返回false。
		isZero	                        如果此Duration长度为零，则返回true。 否则，返回false
		plusDays, minusDays	            在此Duration内添加或减去指定的天数。
		plusMonths, minusMonths	        在此Duration内添加或减去指定的月数。
		plusYears, minusYears	        在Duration内添加或减去指定的年数
		withSeconds	                    以指定的秒数返回此Duration的拷贝。*/
}
