package com.wxuy.example.mapper.tLog;


import com.wxuy.example.annotation.ControllerSqlLog;
import com.wxuy.example.entity.WebLog;
import com.wxuy.example.entity.WebLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WebLogMapper {
    long countByExample(WebLogExample example);

    int deleteByExample(WebLogExample example);

    int deleteByPrimaryKey(Integer id);

    @ControllerSqlLog
    int insert(WebLog record);

    int insertSelective(WebLog record);

    List<WebLog> selectByExampleWithBLOBs(WebLogExample example);

    List<WebLog> selectByExample(WebLogExample example);

    WebLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WebLog record, @Param("example") WebLogExample example);

    int updateByExampleWithBLOBs(@Param("record") WebLog record, @Param("example") WebLogExample example);

    int updateByExample(@Param("record") WebLog record, @Param("example") WebLogExample example);

    int updateByPrimaryKeySelective(WebLog record);

    int updateByPrimaryKeyWithBLOBs(WebLog record);

    int updateByPrimaryKey(WebLog record);
}