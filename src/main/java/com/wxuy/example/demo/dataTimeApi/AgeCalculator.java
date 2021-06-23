package com.wxuy.example.demo.dataTimeApi;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class AgeCalculator {
	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
	public Period calculateAge(LocalDate birthday) {
		LocalDate today = LocalDate.now();
		return Period.between(birthday, today);
	}

	public LocalDate getBirthday() {
		Scanner scanner = new Scanner(System.in);
		LocalDate birthday;
		while (true) {
			System.out.println("Please enter your birthday "
					+ "in yyyy-MM-dd format (e.g. 1980-9-28): ");
			String input = scanner.nextLine();
			try {
				birthday = LocalDate.parse(input, formatter);
				return birthday;
			} catch(DateTimeParseException e) {
				System.out.println("Error! Please try again");
			}
		}
	}

	/**
	 * 在Java Date和Time API的许多类中有两种parse方法。第一个需要格式化实例，第二个则不需要。
	 * 后一个方法会根据默认模式解析日期时间。要使用自己的格式化模式，请使用DateTimeFormatter。
	 * 如果传递的字符串不能被解析，那么解析方法将抛出一个DateTimeParseException。
	 * @param args
	 */
	public static void main(String[] args) {
		AgeCalculator ageCalculator = new AgeCalculator();
		LocalDate birthday = ageCalculator.getBirthday();
		Period age = ageCalculator.calculateAge(birthday);
		System.out.printf("Today you are %d years, %d months"
						+ " and %d days old%n",
				age.getYears(), age.getMonths(), age.getDays());
	}
}
