package com.wxuy.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	// 创建 threadlocal实例 提供线程安全的 safeSdf
	static ThreadLocal<DateFormat> safeSdf = new ThreadLocal<DateFormat>(){
		@Override
		protected SimpleDateFormat initialValue(){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	//线程不安全 改为 threadlocal 中的统一对象 每个线程都拥有自己的SimpleDateFormat对象
//	private static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 用于判空
	 * @param object
	 * @return
	 */
	public static String isNotNull(Object object)
	{
		return (object != null) && (object != "") ? object.toString() : "";
	}

	/**
	 * 判断时间是否在时间段内
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(String  nowTime, String  beginTime, String  endTime) {
		Calendar date = Calendar.getInstance();
		Date nowDate = null;
		try {
			nowDate = safeSdf.get().parse(nowTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		date.setTime(nowDate);

		Calendar begin = Calendar.getInstance();
		Date beginDate = null;
		try {
			beginDate = safeSdf.get().parse(beginTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		begin.setTime(beginDate);

		Calendar end = Calendar.getInstance();
		Date endDate = null;
		try {
			endDate = safeSdf.get().parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		end.setTime(endDate);
		if(date.toString().equals(begin.toString())){
			return true;
		}
		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
}
