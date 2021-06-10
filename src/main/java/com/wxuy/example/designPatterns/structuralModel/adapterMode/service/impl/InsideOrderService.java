package com.wxuy.example.designPatterns.structuralModel.adapterMode.service.impl;


import com.wxuy.example.designPatterns.structuralModel.adapterMode.service.OrderAdapterService;
import com.wxuy.example.designPatterns.structuralModel.adapterMode.service.OrderService;

public class InsideOrderService implements OrderAdapterService {

    private OrderService orderService = new OrderService();

    public boolean isFirst(String uId) {
        return orderService.queryUserOrderCount(uId) <= 1;
    }

}
