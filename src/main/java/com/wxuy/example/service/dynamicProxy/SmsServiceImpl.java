package com.wxuy.example.service.dynamicProxy;

public class SmsServiceImpl implements SmsService{
	@Override
	public String send(String message) {
		System.out.println("send message:" + message);
		return message;
	}

	@Override
	public String push(String message) {
		System.out.println("push message" + message);
		return message;
	}
}
