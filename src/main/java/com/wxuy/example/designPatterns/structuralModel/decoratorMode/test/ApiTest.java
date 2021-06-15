package com.wxuy.example.designPatterns.structuralModel.decoratorMode.test;


import com.wxuy.example.designPatterns.structuralModel.decoratorMode.SsoInterceptor;
import com.wxuy.example.designPatterns.structuralModel.decoratorMode.design.LoginSsoDecorator;

public class ApiTest {
    /**
     * 在装饰类中有两个᯿点的地⽅是；1)继承了处理接⼝、2)提供了构造函数、3)覆盖了⽅法 preHandle 。
     * @param args
     */
    public static void main(String[] args) {

        LoginSsoDecorator ssoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        String request = "1successhuahua";
        boolean success = ssoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
        System.out.println("登录校验：" + request + (success ? " 放行" : " 拦截"));
    }

}
