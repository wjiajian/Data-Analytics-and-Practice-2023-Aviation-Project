package com.qrsoft.controller;

import com.qrsoft.common.Result;
import com.qrsoft.service.WarnFlightHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "年度统计")
@RestController
@RequestMapping("/api/warnFlightHistory")
public class WarnFlightHistoryController {

    @Autowired
    private WarnFlightHistoryService service;

    /**
     * 年度警告分类统计

     */
    @ApiOperation(value = "年度警告分类统计")
    @GetMapping("/annualWarningStatisticsByCategory")
    public Result annualWarningStatisticsByCategory(){
        return service.annualWarningStatisticsByCategory();
    }
    /**
     * 年度警告区域统计

     */
    @ApiOperation(value = "年度警告区域统计")
    @GetMapping("/annualWarningAreaStatistics")
    public Result annualWarningAreaStatistics(){
        return service.annualWarningAreaStatistics();
    }

    /**
     * 管制指令纠错
     */
    @ApiOperation(value = "管制指令纠错")
    @GetMapping("/findWarnTp")
    public Result findWarnTp(){
        return service.findWarnTp();
    }
}
