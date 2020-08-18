package com.wxuy.example.mapper.primary;

import com.wxuy.example.annotation.ControllerSqlLog;
import com.wxuy.example.entity.StraegyUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface StraegyMapper {

    @ControllerSqlLog
    StraegyUser findUserA(String id);

    @ControllerSqlLog
    StraegyUser findUserB(String id);

    @ControllerSqlLog
    StraegyUser findUserC(String id);

}


