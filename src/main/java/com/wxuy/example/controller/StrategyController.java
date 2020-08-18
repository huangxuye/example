package com.wxuy.example.controller;


import com.wxuy.example.annotation.ControllerWebLog;
import com.wxuy.example.service.StrategyServiceContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"测试策略模式"})
@RestController
@RequestMapping(value = "/api/strategy")
public class StrategyController {

    @Autowired
    private StrategyServiceContext strategyServiceContext;

    @ApiOperation(value = "测试策略模式", notes = "根据不同策略获得不同数据库中的用户列表")
    @PostMapping("/strategyType")
    @ControllerWebLog(name = "测试策略模式-根据不同策略获得不同数据库中的用户列表接口",intoDb = true)
    public String getUserName(String strategyType, String id ){
        return strategyServiceContext.getResource(strategyType,id);
    }
}
