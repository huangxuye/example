package com.wxuy.example.demo.dataTimeApi;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TimeZoneDemo1 {
	/**
	 * 互联网数字分配机构（IANA）维护一个可从此网页下载的时区数据库：
	 *
	 * [http://www.iana.org/time-zones](http://www.iana.org/time-zones)
	 * 但为了便于查看，可以访问此Wikipedia页面：
	 * http://en.wikipedia.org/wiki/List_of_tz_database_time_zones
	 *
	 * Java日期和时间API也适用于时区。 抽象类ZoneId（在java.time包中）表示一个区域标识符。
	 * 它有一个名为getAvailableZoneIds的静态方法，它返回所有区域标识符。 下面展示了如何使用这种方法打印所有时区的排序列表。
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		List<String> zoneList = new ArrayList<>(allZoneIds);
		Collections.sort(zoneList);
		for (String zoneId : zoneList) {
			System.out.println(zoneId);
		}
		// alternatively, you can use this line of code to
		// print a sorted list of zone ids
		//getAvailableZoneIds返回586个区域标识符的Set集合。 以下是上述代码中的一部分区域标识符。
		ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);
	}
}