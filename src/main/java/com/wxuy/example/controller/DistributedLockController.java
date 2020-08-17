package com.wxuy.example.controller;

import com.wxuy.example.annotation.ControllerWebLog;
import com.wxuy.example.annotation.DistributeLock;
import com.wxuy.example.entity.BaseRequest;
import com.wxuy.example.entity.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"redis分布式锁测试"})
@RestController
@RequestMapping(value = "/api/redis")
public class DistributedLockController {
    @PostMapping("/distributed_lock")
    @ApiOperation("redis分布式锁测试")
    @ControllerWebLog(name = "redis分布式锁测试", intoDb = true)
    @DistributeLock(key = "distributed_lock_#{baseRequest.channel}", timeout = 10)
    public BaseResponse distributedLock(@RequestBody BaseRequest baseRequest) {
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return BaseResponse.addResult();
    }

}
