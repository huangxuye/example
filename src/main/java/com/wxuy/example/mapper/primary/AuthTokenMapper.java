package com.wxuy.example.mapper.primary;


import com.wxuy.example.annotation.ControllerSqlLog;
import com.wxuy.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AuthTokenMapper {
    @ControllerSqlLog
    boolean loginAuthentication(User user);
}
