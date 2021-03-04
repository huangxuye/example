package com.wxuy.example.service.dynamicProxy;

import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {
		//jdk动态代理
		SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
		SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
		smsService.send("test");
		//cglib动态代理
		AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.gerProxy(AliSmsService.class);
		aliSmsService.send("test");


	}
}
