package com.wxuy.example.service.dynamicProxy;

import org.omg.CORBA.Object;
import org.springframework.cglib.proxy.MethodProxy;

public interface MethodInterceptor {
	//拦截被代理类中的方法
	public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args,
							MethodProxy proxy) throws Throwable;
}
