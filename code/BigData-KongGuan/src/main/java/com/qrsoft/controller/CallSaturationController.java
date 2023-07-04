package com.qrsoft.controller;

import com.qrsoft.common.Result;
import com.qrsoft.service.CallsaturationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "扇区饱和度")
@RestController
@RequestMapping("/api/callSaturation")
public class CallSaturationController {

    @Autowired
    private CallsaturationService service;

    @ApiOperation(value = "获取各个扇区通话饱和度")
    @GetMapping("/findCallSaturation")
    public Result findCallSaturation(){
        return service.findCallSaturation();
    }
}
