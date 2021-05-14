package com.wxuy.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateExample {
	public static void main(String[] args) {
		//Java 8 之前:
/*		Date now = new Date();
		//format yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date  = sdf.format(now);
		System.out.println(String.format("date format : %s", date));

		//format HH:mm:ss
		SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
		String time = sdft.format(now);
		System.out.println(String.format("time format : %s", time));

		//format yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = sdfdt.format(now);
		System.out.println(String.format("dateTime format : %s", datetime));*/

		//Java 8 之后:
		//format yyyy-MM-dd
		LocalDate date = LocalDate.now();
		System.out.println(String.format("date format : %s", date));

		//format HH:mm:ss
		LocalTime time = LocalTime.now().withNano(0);
		System.out.println(String.format("time format : %s", time));

		//format yyyy-MM-dd HH:mm:ss
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dateTime.format(dateTimeFormatter);
		System.out.println(String.format("dateTime format : %s", dateTime));

//		字符串转日期格式
//		Java 8 之前:
//		//已弃用
//		Date date = new Date("2021-01-26");
		//替换为
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date1 = sdf.parse("2021-01-26");
		//Java 8 之后:

		LocalDate date1 = LocalDate.of(2021, 1, 26);
		LocalDate.parse("2021-01-26");

		LocalDateTime dateTime1 = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
		LocalDateTime.parse("2021-01-26 12:12:22");

		LocalTime time1 = LocalTime.of(12, 12, 22);
		LocalTime.parse("12:12:22");
	}
	//日期计算
	//下面仅以一周后日期为例，其他单位（年、月、日、1/2 日、时等等）大同小异。另外，这些单位都在 java.time.temporal.ChronoUnit 枚举中定义。
	//
	//Java 8 之前:
	public void afterDay() throws ParseException {
		//一周后的日期
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DATE, 7);
		Date d = ca.getTime();
		String after = formatDate.format(d);
		System.out.println("一周后日期：" + after);

		//算两个日期间隔多少天，计算间隔多少年，多少月方法类似
		String dates1 = "2021-12-23";
		String dates2 = "2021-02-26";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format.parse(dates1);
		Date date2 = format.parse(dates2);
		int day = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
		System.out.println(dates2 + "和" + dates2 + "相差" + day + "天");
		//结果：2021-12-23和2021-12-23相差300天
	}
	//Java 8 之后:
	public void pushWeek(){
		//一周后的日期
		LocalDate localDate = LocalDate.now();
		//方法1
		LocalDate after = localDate.plus(1, ChronoUnit.WEEKS);
		//方法2
		LocalDate after2 = localDate.plusWeeks(1);
		System.out.println("一周后日期：" + after);

		//算两个日期间隔多少天，计算间隔多少年，多少月
		LocalDate date1 = LocalDate.parse("2021-02-26");
		LocalDate date2 = LocalDate.parse("2021-12-23");
		Period period = Period.between(date1, date2);
		System.out.println("date1 到 date2 相隔："
				+ period.getYears() + "年"
				+ period.getMonths() + "月"
				+ period.getDays() + "天");
		//打印结果是 “date1 到 date2 相隔：0年9月27天”
		//这里period.getDays()得到的天是抛去年月以外的天数，并不是总天数
		//如果要获取纯粹的总天数应该用下面的方法
		long day = date2.toEpochDay() - date1.toEpochDay();
		System.out.println(date2 + "和" + date2 + "相差" + day + "天");
		//打印结果：2021-12-23和2021-12-23相差300天
	}
	//获取指定日期
	//除了日期计算繁琐，获取特定一个日期也很麻烦，比如获取本月最后一天，第一天。
	//
	//Java 8 之前:
	public void getDay() {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		String first = format.format(c.getTime());
		System.out.println("first day:" + first);

		//获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		System.out.println("last day:" + last);

		//当年最后一天
		Calendar currCal = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date time = calendar.getTime();
		System.out.println("last day:" + format.format(time));
	}
	//Java 8 之后:
	public void getDayNew() {
		LocalDate today = LocalDate.now();
		//获取当前月第一天：
		LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
		// 取本月最后一天
		LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
		//取下一天：
		LocalDate nextDay = lastDayOfThisMonth.plusDays(1);
		//当年最后一天
		LocalDate lastday = today.with(TemporalAdjusters.lastDayOfYear());
		//2021年最后一个周日，如果用Calendar是不得烦死。
		LocalDate lastMondayOf2021 = LocalDate.parse("2021-12-			       31").with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
	}
	//JDBC 和 java8
	//现在 jdbc 时间类型和 java8 时间类型对应关系是
	//
	//Date ---> LocalDate
	//Time ---> LocalTime
	//TimesSamp ---> LocalDateTime
	public void zone(){
		//时区
		//时区：正式的时区划分为每隔经度 15° 划分一个时区，全球共 24 个时区，每个时区相差 1 小时。但为了行政上的方便，常将 1 个国家或 1 个省份划在一起，比如我国幅员宽广，大概横跨 5 个时区，实际上只用东八时区的标准时即北京时间为准。

		//java.util.Date 对象实质上存的是 1970 年 1 月 1 日 0 点（ GMT）至 Date 对象所表示时刻所经过的毫秒数。也就是说不管在哪个时区 new Date，它记录的毫秒数都一样，和时区无关。但在使用上应该把它转换成当地时间，这就涉及到了时间的国际化。java.util.Date 本身并不支持国际化，需要借助 TimeZone。

		//北京时间：Wed Jan 27 14:05:29 CST 2021
		Date date = new Date();

		SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//北京时区
		bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		System.out.println("毫秒数:" + date.getTime() + ", 北京时间:" + bjSdf.format(date));

		//东京时区
		SimpleDateFormat tokyoSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tokyoSdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));  // 设置东京时区
		System.out.println("毫秒数:" + date.getTime() + ", 东京时间:" + tokyoSdf.format(date));

		//如果直接print会自动转成当前时区的时间
		System.out.println(date);
		//Wed Jan 27 14:05:29 CST 2021
		//在新特性中引入了 java.time.ZonedDateTime 来表示带时区的时间。它可以看成是 LocalDateTime + ZoneId。

		//当前时区时间
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println("当前时区时间: " + zonedDateTime);

		//东京时间
		ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("JST"));
		ZonedDateTime tokyoTime = zonedDateTime.withZoneSameInstant(zoneId);
		System.out.println("东京时间: " + tokyoTime);

		// ZonedDateTime 转 LocalDateTime
		LocalDateTime localDateTime = tokyoTime.toLocalDateTime();
		System.out.println("东京时间转当地时间: " + localDateTime);

		//LocalDateTime 转 ZonedDateTime
		ZonedDateTime localZoned = localDateTime.atZone(ZoneId.systemDefault());
		System.out.println("本地时区时间: " + localZoned);

		//打印结果
		//当前时区时间: 2021-01-27T14:43:58.735+08:00[Asia/Shanghai]
		//东京时间: 2021-01-27T15:43:58.735+09:00[Asia/Tokyo]
		//东京时间转当地时间: 2021-01-27T15:43:58.735
		//当地时区时间: 2021-01-27T15:53:35.618+08:00[Asia/Shanghai]
	}
}
