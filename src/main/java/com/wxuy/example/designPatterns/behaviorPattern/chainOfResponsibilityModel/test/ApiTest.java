package com.wxuy.example.designPatterns.behaviorPattern.chainOfResponsibilityModel.test;


import com.alibaba.fastjson.JSON;
import com.wxuy.example.designPatterns.behaviorPattern.chainOfResponsibilityModel.AuthLink;
import com.wxuy.example.designPatterns.behaviorPattern.chainOfResponsibilityModel.AuthService;
import com.wxuy.example.designPatterns.behaviorPattern.chainOfResponsibilityModel.impl.Level1AuthLink;
import com.wxuy.example.designPatterns.behaviorPattern.chainOfResponsibilityModel.impl.Level2AuthLink;
import com.wxuy.example.designPatterns.behaviorPattern.chainOfResponsibilityModel.impl.Level3AuthLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

public class ApiTest {

    public static void main(String[] args) throws ParseException {
        Logger logger = LoggerFactory.getLogger(ApiTest.class);
        AuthLink authLink = new Level3AuthLink("1000013", "王工")
                .appendNext(new Level2AuthLink("1000012", "张经理")
                        .appendNext(new Level1AuthLink("1000011", "段总")));

        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", new Date())));

        // 模拟三级负责人审批
        AuthService.auth("1000013", "1000998004813441");
        logger.info("测试结果：{}", "模拟三级负责人审批，王工");
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", new Date())));

        // 模拟二级负责人审批
        AuthService.auth("1000012", "1000998004813441");
        logger.info("测试结果：{}", "模拟二级负责人审批，张经理");
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", new Date())));

        // 模拟一级负责人审批
        AuthService.auth("1000011", "1000998004813441");
        logger.info("测试结果：{}", "模拟一级负责人审批，段总");
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", new Date())));

    }

}
