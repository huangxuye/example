package com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.impl;

import com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.CacheService;
import com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.RedisUtils;

import java.util.concurrent.TimeUnit;

public class CacheServiceImpl implements CacheService {

    private RedisUtils redisUtils = new RedisUtils();

    public String get(String key) {
        return redisUtils.get(key);
    }

    public void set(String key, String value) {
        redisUtils.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        redisUtils.set(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        redisUtils.del(key);
    }

}
