package com.qrsoft.controller;

import com.qrsoft.common.Result;
import com.qrsoft.service.AtcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "扇区操作类")
@RestController
@RequestMapping("/api/atc")
public class AtcController {
    @Autowired
    private AtcService service;
    /**
     * 获取各扇区航班数
     */
    @ApiOperation(value = "获取各扇区航班数")
    @GetMapping("/findSectorSortie")
    public Result findSectorSortie(){
        return service.findSectorSortie();
    }

    /**
     * 根据扇区名称获取该扇区航班数
     * @param planSectorName
     */
    @ApiOperation(value = "根据扇区名称获取该扇区航班数")
    @GetMapping("/findLocusCount")
    public Result findLocusCount(@RequestParam String planSectorName){
        return service.findLocusCount(planSectorName);
    }

    /**
     * 扇区架次数动态统计(饼状图)
     */
    @ApiOperation(value = "扇区架次数动态统计(饼状图)")
    @GetMapping("/findATCTime")
    public Result findATCTime(){
        return service.findATCTime();
    }

}
