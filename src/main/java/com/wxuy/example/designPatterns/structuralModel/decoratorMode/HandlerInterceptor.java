package com.wxuy.example.designPatterns.structuralModel.decoratorMode;

public interface HandlerInterceptor {

    boolean preHandle(String request, String response, Object handler);

}
