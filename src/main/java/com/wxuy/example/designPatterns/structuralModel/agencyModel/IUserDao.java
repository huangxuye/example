package com.wxuy.example.designPatterns.structuralModel.agencyModel;


import com.wxuy.example.designPatterns.structuralModel.agencyModel.agent.Select;

public interface IUserDao {

    @Select("select userName from user where id = #{uId}")
    String queryUserInfo(String uId);

}
