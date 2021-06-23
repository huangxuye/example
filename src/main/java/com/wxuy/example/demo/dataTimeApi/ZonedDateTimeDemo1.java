package com.wxuy.example.demo.dataTimeApi;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeDemo1 {
	public static void main(String[] args) {
	/*	ZonedDateTime类以一个时区为日期时间的构建。例如，以下是一个时区的日期时间:
		2015-12-31T10:59:59+01:00 Europe/Paris
		ZonedDateTime始终是不可变的，时间分量的存储精度为纳秒。
		ZonedDateTIme中一些重要方法的使用与LocalDateTime类似，只是多了一个时区的概念。可自行查阅API。
		像LocalDateTime一样，ZonedDateTime类现在提供静态now和of方法，并构造一个ZonedDateTime实例。
		now方法创建一个ZonedDateTime代表执行的日期和时间。 无参now方法会使用计算机的默认时区创建ZonedDateTime。*/
		ZonedDateTime now = ZonedDateTime.now();
//		now的另一个重载方法允许传递区域标识符：
		ZonedDateTime parisTime =
				ZonedDateTime.now(ZoneId.of("Europe/Paris"));
/*		of方法也有好几个重载的方法。在所有情况下，都需要传递区域标识符。 第一个重载方法允许传递时区日期时间的每个部分，从年份到纳秒。

		public static ZonedDateTime of(int year, int month, int dayOfMonth,
		int hour, int minute, int second, int nanosecond,
		ZoneId zone)
		of方法的第二个重载方法需要LocalDate，LocalTime和ZoneId参数：

		public static ZonedDateTime of(LocalDate date, LocalTime time,
				ZoneId zone)
		of方法的最后一个重载方法需要LocalDateTime和ZoneId参数。

		public static ZonedDateTime of(LocalDateTime datetime, ZoneId zone)
		像LocalDate和LocalDateTime一样，ZonedDateTime提供了使用plusXXX，minusXXX和withXXX方法创建实例拷贝的方法。*/

//		例如，下面代码行创建一个带默认时区的ZonedDateTime，并调用它的minusDays方法以在三天前创建相同的ZonedDateTime。
		ZonedDateTime now1 = ZonedDateTime.now();
		ZonedDateTime threeDaysEarlier = now1.minusDays(3);
	}
}
