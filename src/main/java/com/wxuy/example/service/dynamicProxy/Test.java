package com.wxuy.example.service.dynamicProxy;

public class Test {
	public static void main(String[] args) {
		SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
		smsService.send("java");
		smsService.push("test");
	}
}
