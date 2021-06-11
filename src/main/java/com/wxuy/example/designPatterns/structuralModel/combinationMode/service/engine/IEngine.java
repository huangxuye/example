package com.wxuy.example.designPatterns.structuralModel.combinationMode.service.engine;


import com.wxuy.example.designPatterns.structuralModel.combinationMode.model.aggregates.TreeRich;
import com.wxuy.example.designPatterns.structuralModel.combinationMode.model.vo.EngineResult;

import java.util.Map;

public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, final Map<String, String> decisionMatter);

}
