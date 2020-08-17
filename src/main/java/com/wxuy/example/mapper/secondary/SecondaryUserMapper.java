package com.wxuy.example.mapper.secondary;

import com.wxuy.example.annotation.ControllerSqlLog;
import com.wxuy.example.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SecondaryUserMapper {

    @ControllerSqlLog
    List<City> findAll();
}
