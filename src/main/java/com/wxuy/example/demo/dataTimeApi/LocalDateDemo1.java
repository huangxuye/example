package com.wxuy.example.demo.dataTimeApi;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalDateDemo1 {

	public static void main(String[] args) {
		//要创建代表特定年，月和日的LocalDate，使用of方法，该方法也是静态的。
		// 例如，以下代码创建了一个代表2018年3月7日的LocalDate实例。
		LocalDate date = LocalDate.of(2018, 3, 7);
		//还有一个接受java.time.Month枚举的常量作为第二个参数的of方法。
		// 例如，下面是使用第二种方法重载构造相同日期的代码。
		LocalDate date1 = LocalDate.of(2018, Month.MARCH, 7);
		int dayOfMonth = date.getDayOfMonth();
		Month month = date.getMonth();
		int monthValue = date.getMonthValue();
		int year = date.getYear();
		//ChronoField是一个实现TemporalField接口的枚举，因此可以传递一个ChronoField常量来获取。
		// TemporalField和ChronoField都是java.time.temporal包的一部分。
		// 但是，并非ChronoField中的所有常量都可以get获取，因为并非所有常量都受支持。
		// 例如，传递ChronoField.SECOND_OF_DAY以引发异常。
		// 因此，取而代之，最好使用getMonth，getYear或类似方法来获取LocalDate的组件。
		int year1 = date.get(ChronoField.YEAR);
		//重要
		//此外，还有拷贝LocalDate的方法，例如plusDays，plusYears，minusMonths等等。
		// 例如，要获取表示明天的LocalDate，可以创建一个代表今天的LocalDate，然后调用其plusDays方法。
		LocalDate tomorrow1 = LocalDate.now().plusDays(1);
		//要获取昨天表示的LocalDate，可以使用minusDays方法。
		LocalDate yesterday1 = LocalDate.now().minusDays(1);
		//另外，还有plus和minus方法以更通用的方式获得LocalDate的拷贝。
		// 两者都接受一个int参数和一个TemporalUnit参数。
		//例如，获得一个从今天开始前20年的LocalDate，可以使用这段代码。
		LocalDate pastDate = LocalDate.now().minus(2, ChronoUnit.DECADES);

		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		LocalDate oneDecadeAgo = today.minus(1, ChronoUnit.DECADES);

		System.out.println("Day of month: " + today.getDayOfMonth());
		System.out.println("Today is " + today);
		System.out.println("Tomorrow is " + tomorrow);
		System.out.println("A decade ago was " + oneDecadeAgo);
		System.out.println("Year : " + today.get(ChronoField.YEAR));
		System.out.println("Day of year:" + today.getDayOfYear());
	}
	/*LocalDate类只包括日期没有时间的部分。 它也没有时区。 下表显示了LocalDate中一些重要的方法。
	    方法	                                    描述
		now	                                    静态方法，返回今天的日期
		of	                                    从指定年份，月份和日期创建LocalDate的静态方法
		getDayOfMonth, getMonthValue, getYear	以int形式返回此LocalDate的日，月或年
		getMonth	                            以Month枚举常量返回此LocalDate的月份
		plusDays, minusDays	                    给LocalDate添加或减去指定的天数
		plusWeeks, minusWeeks	                给LocalDate添加或减去指定的星期数
		plusMonths, minusMonths	                给LocalDate添加或减去指定的月份数
		plusYears, minusYears	                给LocalDate添加或减去指定的年数
		isLeapYear	                            检查LocalDate指定的年份是否为闰年
		isAfter, isBefore	                    检查此LocalDate是在给定日期之后还是之前
		lengthOfMonth	                        返回此LocalDate中月份的天数
		withDayOfMonth	                        返回此LocalDate的拷贝，将月份中的某天设置为给定值
		withMonth	                            返回此LocalDate的拷贝，其月份设置为给定值
		withYear	                            返回此LocalDate的拷贝，并将年份设置为给定值*/
}