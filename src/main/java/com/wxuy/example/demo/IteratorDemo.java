package com.wxuy.example.demo;

import com.wxuy.example.entity.City;

import java.util.*;

public class IteratorDemo {
	public static void main(String[] args) {
//		Map<Integer, String> map = new HashMap<>();
//		map.put(1,"test");
//		map.put(2,"test2");
//		map.put(3,"test3");
//		Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
//		while(iterator.hasNext()){
//			Map.Entry<Integer, String> next = iterator.next();
//			System.out.println(next.getKey()+":"+next.getValue());
//
//		}

		List<City> cityList = new ArrayList<City>();
		cityList.add(new City().setId(1).setCity_name("杭州").setDescription("是杭州呀"));
		cityList.add(new City().setId(2).setCity_name("江苏").setDescription("是江苏呀"));
		cityList.add(new City().setId(3).setCity_name("上海").setDescription("是上海呀"));
		Iterator<City> iterator1 = cityList.iterator();
		while(iterator1.hasNext()){
			System.out.println(iterator1.next().getCity_name());
		}



	}
}
