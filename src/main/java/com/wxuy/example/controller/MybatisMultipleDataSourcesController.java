package com.wxuy.example.controller;

import com.wxuy.example.annotation.ControllerWebLog;
import com.wxuy.example.entity.City;
import com.wxuy.example.entity.User;
import com.wxuy.example.mapper.primary.PrimaryUserMapper;
import com.wxuy.example.mapper.secondary.SecondaryUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"测试Mybatis多数据源配置"})
@RestController
@RequestMapping(value = "/api/mybatis")
public class MybatisMultipleDataSourcesController {

    @Autowired
    private PrimaryUserMapper primaryUserMapper;
    @Autowired
    private SecondaryUserMapper secondaryUserMapper;

    @ApiOperation(value = "默认数据源", notes = "获得所有用户列表")
    @PostMapping("/primary")
    @ControllerWebLog(name = "默认数据源-获得所有用户列表接口",intoDb = true)
    public Object primary(){
        List<User> list = primaryUserMapper.findAll();
        return list;
    }
    @ApiOperation(value = "备选数据源", notes = "获得所有城市列表")
    @PostMapping("/secondary")
    @ControllerWebLog(name = "备选数据源-获得所有城市列表接口",intoDb = true)
    public Object secondary  (){
        List<City> list = secondaryUserMapper.findAll();
        return list;
    }



//    @ApiOperation(value = "根据经纬度、室内室外查询是否有5G覆盖", notes = "根据经纬度经纬度、室内室外是否有5G覆盖")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "lng",value = "经度",paramType = "path",dataType = "String"),
//            @ApiImplicitParam(name = "lat",value = "纬度",paramType = "path",dataType = "String"),
//            @ApiImplicitParam(name = "type",value = "室内室外（1室内 0 室外）",paramType = "path",dataType = "String")
//    })
//    @GetMapping("/location5GCover/lng/{lng}/lat/{lat}/type/{type}")
//    public Result<Net5GServiceLocation5GCoveResp> queryLocation5GCover(@PathVariable String lng, @PathVariable String lat, @PathVariable String type) {
//        return net5GService.queryNet5GServiceLocation5GCove(lng,lat,type);
//    }

//    @Autowired
//    private SpamSMSInterfaceService spamSMSInterfaceService;
//
//
//    @ApiOperation(value = "垃圾短信告警名单话单日志查询接口封装", notes = "")
//    @PostMapping("/qryAlarmList")
//    public AlarmListResp qryAlarmList(@RequestBody AlarmListReq alarmListReq){
//        return spamSMSInterfaceService.qryAlarmList(alarmListReq);
//    }


}
