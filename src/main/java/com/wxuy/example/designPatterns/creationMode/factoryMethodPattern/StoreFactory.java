package com.wxuy.example.designPatterns.creationMode.factoryMethodPattern;

import com.wxuy.example.designPatterns.creationMode.factoryMethodPattern.impl.CardCommodityService;
import com.wxuy.example.designPatterns.creationMode.factoryMethodPattern.impl.CouponCommodityService;
import com.wxuy.example.designPatterns.creationMode.factoryMethodPattern.impl.GoodsCommodityService;

public class StoreFactory {

    public ICommodity getCommodityService(Integer commodityType) {
        if (null == commodityType) return null;
        if (1 == commodityType) return new CouponCommodityService();
        if (2 == commodityType) return new GoodsCommodityService();
        if (3 == commodityType) return new CardCommodityService();
        throw new RuntimeException("不存在的商品服务类型");
    }

}
