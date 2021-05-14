package com.wxuy.example.demo;

import com.wxuy.example.entity.Animal;
import com.wxuy.example.entity.Zoo;
import jdk.nashorn.internal.ir.CallNode;

import java.util.List;
import java.util.Optional;

public class OptionalExample {
	public static void main(String[] args) {
		//ofNullable 方法和of方法唯一区别就是当 value 为 null 时，ofNullable 返回的是EMPTY，of 会抛出 NullPointerException 异常。
		// 如果需要把 NullPointerException 暴漏出来就用 of，否则就用 ofNullable。
		//flatMap() 参数返回值如果是 null 会抛 NullPointerException，而 map() 返回EMPTY。
		Animal animal = new Animal();
		animal.setAge("11").setName("mm").setSex("男").setSpecies("dog");
		Zoo zoo = new Zoo();
		zoo.setAnimal(animal);
		String age = Optional.ofNullable(zoo).map(Zoo::getAnimal).filter(animal1 -> "dog".equals(animal1.getSpecies())).map(Animal::getAge).orElse("3");
		System.out.println(age);
	}
}
