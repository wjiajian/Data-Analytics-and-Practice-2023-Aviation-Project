package com.qrsoft.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qrsoft.common.Result;
import com.qrsoft.common.ResultConstants;
import com.qrsoft.entity.Aftn;
import com.qrsoft.mapper.AftnMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AftnService extends ServiceImpl<AftnMapper, Aftn> {

    public Result getAirPortCount() {
        List<Aftn> aftnList = baseMapper.getAftnList();
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS, aftnList);
    }
}
