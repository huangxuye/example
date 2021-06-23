package com.wxuy.example.demo.dataTimeApi;

import java.time.*;

public class DurationDemo2 {
	public static void main(String[] args) {
		//下面的代码在两个ZoneDateTime之间创建一个Duration，具有相同的日期和时间，但时区不同。
		ZonedDateTime zdt1 = ZonedDateTime.of(
				LocalDateTime.of(2015, Month.JANUARY, 1,
						8, 0),
				ZoneId.of("America/Denver"));
		ZonedDateTime zdt2 = ZonedDateTime.of(
				LocalDateTime.of(2015, Month.JANUARY, 1,
						8, 0),
				ZoneId.of("America/Toronto"));

		Duration duration = Duration.between(zdt1, zdt2);
		System.out.printf("There are %d hours and %d minutes.%n",
				duration.toHours(),
				duration.toMinutes() % 60);
	}
}
