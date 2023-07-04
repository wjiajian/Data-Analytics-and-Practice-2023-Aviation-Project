package com.qrsoft.controller;

import com.qrsoft.common.Result;
import com.qrsoft.service.MultiRadarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "综合航迹数据")
@RestController
@RequestMapping("/api/multiRadar")
public class MultiRadarController {
    @Autowired
    private MultiRadarService service;
    /**
     * 查询综合航迹数据
     */
    @GetMapping("/findMultRadar")
    public Result findMultRadar(){
        return service.findMultRadar();
    }
}
