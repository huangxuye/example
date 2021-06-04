package com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.factory.impl;

import com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.factory.ICacheAdapter;
import com.wxuy.example.designPatterns.creationMode.abstractFactoryPattern.matter.EGM;

import java.util.concurrent.TimeUnit;

public class EGMCacheAdapter implements ICacheAdapter {

    private EGM egm = new EGM();

    public String get(String key) {
        return egm.gain(key);
    }

    public void set(String key, String value) {
        egm.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        egm.setEx(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        egm.delete(key);
    }
}
