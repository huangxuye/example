package com.wxuy.example.designPatterns.behaviorPattern.commandMode.cuisine.impl;

import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cook.ICook;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cuisine.ICuisine;

public class SiChuanCuisine implements ICuisine {

    private ICook cook;

    public SiChuanCuisine(ICook cook) {
        this.cook = cook;
    }

    public void cook() {
        cook.doCooking();
    }

}