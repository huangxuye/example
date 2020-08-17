package com.wxuy.example.mapper.primary;

import com.wxuy.example.annotation.ControllerSqlLog;
import com.wxuy.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PrimaryUserMapper {
    @ControllerSqlLog
    List<User> findAll();
}
