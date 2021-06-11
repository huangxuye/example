package com.wxuy.example.designPatterns.structuralModel.bridgeMode.test;


import com.wxuy.example.designPatterns.structuralModel.bridgeMode.channel.Pay;
import com.wxuy.example.designPatterns.structuralModel.bridgeMode.channel.WxPay;
import com.wxuy.example.designPatterns.structuralModel.bridgeMode.channel.ZfbPay;
import com.wxuy.example.designPatterns.structuralModel.bridgeMode.mode.PayFaceMode;
import com.wxuy.example.designPatterns.structuralModel.bridgeMode.mode.PayFingerprintMode;

import java.math.BigDecimal;

public class ApiTest {

    public static void main(String[] args) {

        System.out.println("\r\n模拟测试场景；微信支付、人脸方式。");
        Pay wxPay = new WxPay(new PayFaceMode());
        wxPay.transfer("weixin_1092033111", "100000109893", new BigDecimal(100));

        System.out.println("\r\n模拟测试场景；支付宝支付、指纹方式。");
        Pay zfbPay = new ZfbPay(new PayFingerprintMode());
        zfbPay.transfer("jlu19dlxo111","100000109894",new BigDecimal(100));

    }

}
