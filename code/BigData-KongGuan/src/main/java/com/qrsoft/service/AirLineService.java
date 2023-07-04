package com.qrsoft.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qrsoft.common.Result;
import com.qrsoft.common.ResultConstants;
import com.qrsoft.entity.AirLine;
import com.qrsoft.mapper.AirLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AirLineService extends ServiceImpl<AirLineMapper,AirLine> {


    /**
     * 查询所有航线信息
     */
    public Result findAll(){
        List<AirLine> airLines = baseMapper.selectList(null);
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,airLines);
    }

    /**
     * 查询国内航班数前十的航线
     */
    public Result findBylimit(){
        List<AirLine> airLines = baseMapper.findBylimit();
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,airLines);
    }

    /**
     * 查询总航线数
     */
    public Result findLineCount(){
        Integer lineCount = baseMapper.findLineCount();
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,lineCount);
    }

}