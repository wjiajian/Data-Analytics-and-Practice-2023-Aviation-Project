package com.qrsoft.controller;

import com.qrsoft.common.Result;
import com.qrsoft.service.AirLineService;
import com.qrsoft.service.TimeTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;


@Api(tags = "航线操作类")
@RestController
@RequestMapping("/api/airLine")

public class AirLineController {

    @Autowired
    private AirLineService service;

    @Autowired
    private TimeTaskService timeTaskService;

    //获取所有航线
    @ApiOperation(value = "获取所有航线")
    @GetMapping("/findAll")
    public Result findAll(){
        return service.findAll();
    }

    //获取从青岛起飞航班数前十的航线
    @ApiOperation(value = "获取从青岛起飞航班数前十的航线")
    @GetMapping("/findByLimit")
    public Result findByLimit(){
        return service.findBylimit();
    }

    //查询总航线数
    @ApiOperation(value = "查询总航线数")
    @GetMapping("/findLineCount")
    public Result findLineCount(){
        return service.findLineCount();
    }

}