package com.wxuy.example.demo.dataTimeApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeFormatterDemo1 {
	/**
	 * 可以使用java.time.format.DateTimeFormatter格式化本地或时区日期时间。
	 * LocalDate，LocalDateTime，LocalTime和ZoneDateTime类提供具有以下签名的格式方法。
	 *
	 * public java.lang.String format(java.time.format.DateTimeFormatter
	 *         formatter)
	 * 很明显，要格式化日期或时间，必须首先创建DateTimeFormatter实例。
	 *
	 * 下面的代码使用两个格式化实例格式化当前日期。
	 * @param args
	 */
	public static void main(String[] args) {
		DateTimeFormatter formatter1 = DateTimeFormatter
				.ofLocalizedDateTime(FormatStyle.MEDIUM);
		LocalDateTime example = LocalDateTime.of(
				2000, 3, 19, 10, 56, 59);
		System.out.println("Format 1: " + example
				.format(formatter1));

		DateTimeFormatter formatter2 = DateTimeFormatter
				.ofPattern("MMMM dd, yyyy HH:mm:ss");
		System.out.println("Format 2: " +
				example.format(formatter2));
//		DateTimeFormatter formatter3 = DateTimeFormatter
//				.ofLocalizedDateTime(FormatStyle.FULL);
//		System.out.println("Format 3: " +
//				example.format(formatter3));
		DateTimeFormatter formatter4 = DateTimeFormatter
				.ofLocalizedDateTime(FormatStyle.LONG);
		System.out.println("Format 4: " +
				example.format(formatter4));
		DateTimeFormatter formatter5 = DateTimeFormatter
				.ofLocalizedDateTime(FormatStyle.SHORT);
		System.out.println("Format 5: " +
				example.format(formatter5));
	}
}