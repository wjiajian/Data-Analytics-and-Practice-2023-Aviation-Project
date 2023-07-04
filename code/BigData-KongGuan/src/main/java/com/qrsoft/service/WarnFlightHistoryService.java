package com.qrsoft.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qrsoft.common.Result;
import com.qrsoft.common.ResultConstants;
import com.qrsoft.entity.WarnFlightHistory;
import com.qrsoft.mapper.WarnFlightHistoryMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WarnFlightHistoryService extends ServiceImpl<WarnFlightHistoryMapper, WarnFlightHistory> {

    /**
     * 年度警告区域统计
     */
    public Result annualWarningAreaStatistics(){

        List<WarnFlightHistory> warnFlightHistories = baseMapper.annualWarningAreaStatistics();
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,warnFlightHistories);
    }

    /**
     * 年度警告分类统计
     */
    public Result annualWarningStatisticsByCategory(){
        List<WarnFlightHistory> warnFlightHistories = baseMapper.annualWarningStatisticsByCategory();
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,warnFlightHistories);
    }


    /**
     * 管制指令纠错
     */
    public Result findWarnTp(){
        List<HashMap<String, Object>> result = new ArrayList<>();
        List<HashMap<String, Object>> warnTp = baseMapper.findWarnTp();
        for (HashMap<String,Object> hm :warnTp){
            String gj_acids = (String)hm.get("gj_acids");
            String[] split = gj_acids.split("-");
            System.out.println(split.length);
            if(split.length>=2) {
                Integer warn = baseMapper.getWarn(split[0], split[1]);
                if(warn >=2){
                    result.add(hm);
                }
            }
        }
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,result);
    }
}
