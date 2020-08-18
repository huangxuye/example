package com.wxuy.example.service.strategyImpl;

import com.wxuy.example.mapper.primary.StraegyMapper;
import com.wxuy.example.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("C")
public class ResourceC implements StrategyService {
    @Autowired
    private StraegyMapper straegyMapper;

    @Override
    public String getUserName(String id) {
        return straegyMapper.findUserC(id).getName();
    }
}
