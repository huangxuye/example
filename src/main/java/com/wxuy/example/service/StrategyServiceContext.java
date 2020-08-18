package com.wxuy.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class StrategyServiceContext {
    @Autowired
    private final Map<String, StrategyService> strategyMap = new ConcurrentHashMap<>();

    public StrategyServiceContext(Map<String, StrategyService> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v)-> this.strategyMap.put(k, v));
    }

    public String getResource(String strategyType,String poolId){
        return strategyMap.get(strategyType).getUserName(poolId);
    }
}
