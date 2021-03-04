package com.wxuy.example.service.dynamicProxy;

public class AliSmsService {
	public String send(String message){
		System.out.println("send message:" + message);
		return message;
	}
}
