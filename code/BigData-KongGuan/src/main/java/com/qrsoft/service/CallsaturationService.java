package com.qrsoft.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qrsoft.common.Result;
import com.qrsoft.common.ResultConstants;
import com.qrsoft.entity.Callsaturation;
import com.qrsoft.mapper.CallsaturationMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CallsaturationService extends ServiceImpl<CallsaturationMapper, Callsaturation> {
    /**
     * 查询各扇区通话饱和度
     * @return
     */
    public Result findCallSaturation(){
        Map<String,String> map = new HashMap<>();
        List<Callsaturation> callSaturation = baseMapper.findCallSaturation();

        for (Callsaturation c:callSaturation ) {
            map.put(c.getThbhSect(),String.valueOf((Double.parseDouble(c.getThbhValue())*100)));
        }
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,map);
    }
}
