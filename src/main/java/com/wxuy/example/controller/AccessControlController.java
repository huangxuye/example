package com.wxuy.example.controller;

import com.wxuy.example.annotation.AuthToken;
import com.wxuy.example.annotation.ControllerWebLog;
import com.wxuy.example.entity.Requester;
import com.wxuy.example.entity.User;
import com.wxuy.example.mapper.primary.AuthTokenMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"权限控制"})
@RestController
@RequestMapping(value = "/api/aop")
public class AccessControlController {

    /**
     * 无需校验，不加注解
     */
    @ApiOperation("权限控制测试-游客")
    @ControllerWebLog(name = "权限控制测试", intoDb = true)
    @PostMapping("hello")
    @AuthToken( interview_method = "tourist" )
    public String hello(@RequestBody User req) {

        return "hi~ 我不需要用户权限";
    }

    /**
     * 需要登录校验，加上注解，但不传所需角色
     */
    @ApiOperation("权限控制测试-用户")
    @ControllerWebLog(name = "权限控制测试", intoDb = true)
    @PostMapping("user")
    @AuthToken( interview_method = "user" )
    public String user(@RequestBody User req) {

        return "hi~ 我需要登陆后才可以访问";

    }

    /**
     * 需要角色校验，加上注解，并且写入两个角色，本文演示两个角色有一个即可访问，当然写一个可以。
     * 注：若想两个角色同时具有，修改后文的逻辑判断即可。
     * 若需要更复杂的逻辑操作，推荐使用Spring Security框架。
     */
    @ApiOperation("权限控制测试-管理员")
    @ControllerWebLog(name = "权限控制测试", intoDb = true)
    @PostMapping("admin")
    @AuthToken( interview_method = "admin" )
    public String admin(@RequestBody User req) {

        return "hi~ 我需要管理员身份才可以访问";
    }

}
