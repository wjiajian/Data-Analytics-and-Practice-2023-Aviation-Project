package com.qrsoft.controller;

import com.qrsoft.common.Result;
import com.qrsoft.service.WarnSimilarHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "航班告警")
@RestController
@RequestMapping("/api/warnSimilarHistory")
public class WarnSimilarHistoryController {
    @Autowired
    private WarnSimilarHistoryService service;
    /**
     * 查询相似航班告警
     */
    @ApiOperation(value = "查询相似航班告警")
    @GetMapping("/findWarnSimilarHistory")
    public Result findWarnSimilarHistory(){
        return service.findWarnSimilarHistory();
    }
    /**
     * 根据扇区号查询相似航班告警
     */
    @ApiOperation(value = "根据扇区号查询相似航班")
    @GetMapping("/findWarnSimilarOfATC")
    public Result findWarnSimilarOfATC(@RequestParam String sectorName){
        return service.findWarnSimilarOfATC(sectorName);
    }
    /**
     * 根据扇区号查询相似航班告警总数
     */
    @ApiOperation(value = "根据扇区号查询相似航班告警总数")
    @GetMapping("/findWarnSimilarOfATCCount")
    public Result findWarnSimilarOfATCCount(@RequestParam String sectorName){
        return service.findWarnSimilarOfATCCount(sectorName);
    }
}