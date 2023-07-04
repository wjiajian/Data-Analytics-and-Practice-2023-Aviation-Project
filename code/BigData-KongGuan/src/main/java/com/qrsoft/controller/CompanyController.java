package com.qrsoft.controller;

import com.qrsoft.common.Result;
import com.qrsoft.service.AftnService;
import com.qrsoft.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "航空公司航班数，和航班延误数")
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @Autowired
    private AftnService aftnService;

    /**
     * 查询指挥航空公司航班数，和航班延误数
     */
    @ApiOperation(value = "查询指挥航空公司航班数，和航班延误数")
    @GetMapping("/findCompanyDelay")
    public Result findCompanyDelay(){
        return service.findCompanyDelay();
    }

    @ApiOperation(value = "查询指挥航空公司架次数占比")
    @GetMapping("/findCompanyByCompanyAll")
    public Result findCompanyByCompanyAll(){
        return service.findCompanyByCompanyAll();
    }

    @ApiOperation(value = "机场当前负荷统计")
    @GetMapping("/getAirPortCount")
    public Result getAirPortCount(){
        return aftnService.getAirPortCount();
    }
}