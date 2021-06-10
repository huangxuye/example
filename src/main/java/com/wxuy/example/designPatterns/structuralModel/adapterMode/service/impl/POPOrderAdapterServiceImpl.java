package com.wxuy.example.designPatterns.structuralModel.adapterMode.service.impl;

import com.wxuy.example.designPatterns.structuralModel.adapterMode.service.OrderAdapterService;
import com.wxuy.example.designPatterns.structuralModel.adapterMode.service.POPOrderService;

public class POPOrderAdapterServiceImpl implements OrderAdapterService {

    private POPOrderService popOrderService = new POPOrderService();

    public boolean isFirst(String uId) {
        return popOrderService.isFirstOrder(uId);
    }

}
