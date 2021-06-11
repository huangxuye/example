package com.wxuy.example.designPatterns.structuralModel.combinationMode.service.logic.impl;


import com.wxuy.example.designPatterns.structuralModel.combinationMode.service.logic.BaseLogic;

import java.util.Map;

public class UserAgeFilter extends BaseLogic {

    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("age");
    }

}
