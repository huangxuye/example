package com.wxuy.example.demo.dataTimeApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimeDemo1 {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime sameTimeTomorrow = now.plusHours(24);

		System.out.println(sameTimeTomorrow);
	}
/*	LocalDateTime类是一个没有时区的日期时间的构建。下表显示了LocalDateTime中一些重要的方法。
	这些方法类似于LocalDate的方法，以及用于修改时间部分的一些其他方法，
	例如在LocalDate中不可用的plusHours，plusMinutes和plusSeconds。
	方法	                                描述
	now	                                返回当前日期和时间的静态方法。
	of	                                从指定年份，月份，日期，小时，分钟，秒和毫秒创建LocalDateTime的静态方法。
	getYear, getMonthValue, getDayOfMonth,
	getHour, getMinute, getSecond	    以int形式返回此LocalDateTime的年，月，日，小时，分钟或秒部分。
	plusDays, minusDays	                给当前LocalDateTime添加或减去指定的天数。
	plusWeeks, minusWeeks	            给当前LocalDateTime添加或减去指定的周数。
	plusMonths, minusMonths	            给当前LocalDateTime添加或减去指定的月数。
	plusYears, minusYears	            给当前LocalDateTime添加或减去指定的年数。
	plusHours, minusHours	            给当前LocalDateTime添加或减去指定的小时数
	plusMinutes, minusMinutes	        给当前LocalDateTime添加或减去指定的分钟数
	plusSeconds, minusSeconds	        给当前LocalDateTime添加或减去指定的秒数
	IsAfter, isBefore	                检查此LocalDateTime是否在指定的日期时间之后或之前
	withDayOfMonth	                    返回此LocalDateTime的拷贝，并将月份中的某天设置为指定值
	withMonth, withYear	                返回此LocalDateTime的拷贝，其月或年设置为指定值
	withHour, withMinute, withSecond	返回此LocalDateTime的拷贝，其小时/分钟/秒设置为指定值*/

/*	要创建具有特定日期和时间的LocalDateTime，请使用of方法。
	此方法有多个重载，并允许传递日期时间或LocalDate和LocalTime的单个部分。 以下是一些方法的签名。
	public static LocalDateTime of(int year, int month, int dayOfMonth,
								   int hour, int minute)

	public static LocalDateTime of(int year, int month, int dayOfMonth,
								   int hour, int minute)

	public static LocalDateTime of(int year, Month month,
								   int dayOfMonth, int hour, int minute)

	public static LocalDateTime of(int year, Month month,
								   int dayOfMonth, int hour, int minute)

	public static LocalDateTime of(LocalDate date, LocalTime time)*/
}
