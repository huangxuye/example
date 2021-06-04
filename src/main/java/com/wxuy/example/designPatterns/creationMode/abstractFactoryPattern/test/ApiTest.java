package com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.test;

import com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.CacheService;
import com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.factory.JDKProxy;
import com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.factory.impl.EGMCacheAdapter;
import com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.factory.impl.IIRCacheAdapter;
import com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.impl.CacheServiceImpl;

public class ApiTest {

    public static void main(String[] args) throws Exception {

        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "小傅哥");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "小傅哥");
        String val02 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + val02);

    }

}
