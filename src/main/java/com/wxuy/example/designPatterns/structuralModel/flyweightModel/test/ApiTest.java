package com.wxuy.example.designPatterns.structuralModel.flyweightModel.test;

import com.alibaba.fastjson.JSON;
import com.wxuy.example.designPatterns.structuralModel.flyweightModel.Activity;
import com.wxuy.example.designPatterns.structuralModel.flyweightModel.ActivityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiTest {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(ApiTest.class);

        ActivityController activityController = new ActivityController();
        for (int idx = 0; idx < 10; idx++) {
            Long req = 10001L;
            Activity activity = activityController.queryActivityInfo(req);
            logger.info("测试结果：{} {}", req, JSON.toJSONString(activity));
            Thread.sleep(1200);
        }
    }
}
