package com.qrsoft.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qrsoft.common.Result;
import com.qrsoft.common.ResultConstants;
import com.qrsoft.entity.MultiRadar;
import com.qrsoft.mapper.MultiRadarMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiRadarService extends ServiceImpl<MultiRadarMapper, MultiRadar> {
    /**
     * 查询综合航迹数据
     */
    public Result findMultRadar(){
        List<MultiRadar> multiRadars = baseMapper.selectList(null);
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,multiRadars);
    }
}
