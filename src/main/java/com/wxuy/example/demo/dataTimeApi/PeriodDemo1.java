package com.wxuy.example.demo.dataTimeApi;

import java.time.LocalDate;
import java.time.Period;

public class PeriodDemo1 {
	public static void main(String[] args) {
		LocalDate dateA = LocalDate.of(1978, 8, 26);
		LocalDate dateB = LocalDate.of(1988, 9, 28);
		Period period = Period.between(dateA, dateB);
		System.out.printf("Between %s and %s"
						+ " there are %d years, %d months"
						+ " and %d days%n", dateA, dateB,
				period.getYears(),
				period.getMonths(),
				period.getDays());
	}
	/*	Period类基于日期的时间数量构建，例如五天，一周或三年。 下面列出了一些重要的方法。
		方法	                                描述
		between	                            在两个LocalDates之间创建一个Period示例
		ofDays, ofWeeks, ofMonths, ofYears	创建代表给定天数/周/月/年的Period实例
		of	                                根据给定的年数，月数和天数创建一个Period实例
		getDays, getMonths, getYears	    以int形式返回此Period的天数/月/年
		isNegative	                        如果此Period的三个部分中的任何一个为负数，则返回true。 否则返回false
		isZero	                            如果此Period的所有三个部分均为零，则返回true。 否则，返回false
		plusDays, minusDays	                在此Period上添加或减去给定的天数
		plusMonths, minusMonths	            在此Period上增加或减去给定的月数
		plusYears, minusYears	            在此Period增加或减去给定的年数
		withDays	                        以指定的天数返回此Period的拷贝
		withMonths	                        以指定的月数返回此Period的拷贝
		withYears	                        以指定的年数返回此Period的拷贝*/
}
