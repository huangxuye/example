package com.wxuy.example.service.strategyImpl;

import com.wxuy.example.mapper.primary.StraegyMapper;
import com.wxuy.example.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("B")
public class ResourceB implements StrategyService {
    @Autowired
    private StraegyMapper straegyMapper;

    @Override
    public String getUserName(String id) {
        return straegyMapper.findUserB(id).getName();
    }

}
