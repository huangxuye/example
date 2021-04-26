package com.wxuy.example.demo;

public class StackTraceElementDemo {
	public static void main(String[] args) {
		//利用了查询当前线程堆栈中的信息辨别该代码的调用顺序 被调用 调用方
		StackTraceElement stack[] = Thread.currentThread().getStackTrace();
		String callName=stack[2].getClassName();
		System.out.println(callName);
		for (StackTraceElement ste:stack){
			System.out.println("called by "+ste.getClassName()+"."+ste.getMethodName()+"/"+ste.getFileName());
		}
	}
}
