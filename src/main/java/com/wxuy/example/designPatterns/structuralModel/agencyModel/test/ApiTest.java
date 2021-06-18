package com.wxuy.example.designPatterns.structuralModel.agencyModel.test;

import com.wxuy.example.designPatterns.structuralModel.agencyModel.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApiTest {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ApiTest.class);
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        IUserDao userDao = beanFactory.getBean("userDao", IUserDao.class);
        String res = userDao.queryUserInfo("100001");
        logger.info("测试结果：{}", res);
    }

}
