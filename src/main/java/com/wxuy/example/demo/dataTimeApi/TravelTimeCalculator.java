package com.wxuy.example.demo.dataTimeApi;

import java.time.*;

public class TravelTimeCalculator {
	/**
	 * 作为一个更复杂的例子，下面的代码显示了一个公交车旅行时间计算器。
	 * 它有一个方法calculateTravelTime，它需要一个离开的ZonedDateTime实例和一个到达的ZonedDateTime实例。
	 * 该代码调用calculateTravelTime方法两次。
	 * 这两次公交车都在丹佛早上8点从科罗拉多州丹佛出发，并于多伦多时间第二天早上8点抵达多伦多。
	 * 公交车首次于2014年3月8日启程，第二次于2014年3月18日启程。
	 *
	 * 两种情况下的旅行时间是多少?
	 * @param args
	 */
	public static void main(String[] args) {
		TravelTimeCalculator calculator =
				new TravelTimeCalculator();
		ZonedDateTime departure1 = ZonedDateTime.of(
				LocalDateTime.of(2014, Month.MARCH, 8,
						8, 0),
				ZoneId.of("America/Denver"));
		ZonedDateTime arrival1 = ZonedDateTime.of(
				LocalDateTime.of(2014, Month.MARCH, 9,
						8, 0),
				ZoneId.of("America/Toronto"));
		Duration travelTime1 = calculator
				.calculateTravelTime(departure1, arrival1);
		System.out.println("Travel time 1: "
				+ travelTime1.toHours() + " hours");

		ZonedDateTime departure2 = ZonedDateTime.of(
				LocalDateTime.of(2014, Month.MARCH, 18,
						8, 0),
				ZoneId.of("America/Denver"));
		ZonedDateTime arrival2 = ZonedDateTime.of(
				LocalDateTime.of(2014, Month.MARCH, 19,
						8, 0),
				ZoneId.of("America/Toronto"));
		Duration travelTime2 = calculator
				.calculateTravelTime(departure2, arrival2);
		System.out.println("Travel time 2: "
				+ travelTime2.toHours() + " hours");
	}
	public Duration calculateTravelTime(
			ZonedDateTime departure, ZonedDateTime arrival) {
		return Duration.between(departure, arrival);
	}
/*	运行结果为：

	Travel time 1: 21 hours

	Travel time 2: 22 hours

	为什么有这个区别？ 因为2014年的夏令时从3月9日星期日凌晨2点开始。 因此，在2014年3月8日至2014年3月9日之间“失去”了一小时。*/
}
