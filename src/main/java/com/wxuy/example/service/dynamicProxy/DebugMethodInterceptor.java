package com.wxuy.example.service.dynamicProxy;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DebugMethodInterceptor implements MethodInterceptor {
	/**
	 *
	 * @param obj    被代理对象（需增强对象）
	 * @param method 被拦截方法（需增强方法）
	 * @param args   方法入参
	 * @param proxy  用于调用原始方法
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("before method:" + method.getName());
		Object result = proxy.invokeSuper(obj,args);
		System.out.println("after method:" + method.getName());
		return result;
	}
}
