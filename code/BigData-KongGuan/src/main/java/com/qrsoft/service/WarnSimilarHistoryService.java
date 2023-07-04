package com.qrsoft.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qrsoft.common.Result;
import com.qrsoft.common.ResultConstants;
import com.qrsoft.entity.MultiRadar;
import com.qrsoft.entity.WarnSimilarHistory;
import com.qrsoft.mapper.MultiRadarMapper;
import com.qrsoft.mapper.WarnSimilarHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarnSimilarHistoryService extends ServiceImpl<WarnSimilarHistoryMapper, WarnSimilarHistory> {

    @Autowired
    private MultiRadarMapper multiRadarMapper;
    /**
     * 查询相似航班告警
     */
    public Result findWarnSimilarHistory(){
        List<WarnSimilarHistory> warnSimilarHistory = baseMapper.findWarnSimilarHistory();
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,warnSimilarHistory);
    }
    /**
     * 根据扇区号查询相似航班
     */
    public Result findWarnSimilarOfATC(String sectorName){
        QueryWrapper<MultiRadar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("section",sectorName);
        List<MultiRadar> list = multiRadarMapper.selectList(queryWrapper);

        List<Map<String,String>> result = new ArrayList<>();
        System.out.println(list);

        for(MultiRadar m1 :list){
            for(MultiRadar m2:list){
                String acid1 = m1.getAcid();
                String substring = acid1.substring(0,3);
                String acid2 = m2.getAcid();
                if(acid2.startsWith(substring)){
                    if(acid1.equals(acid2)){
                        break;
                    }
                    HashMap<String, String> res = new HashMap<>();
                    res.put("gj", acid1 + "-" + acid2);
                    res.put("gjSector",sectorName);
                    result.add(res);
                    break;
                }
            }
        }
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,result);
    }
    /**
     * 查询航班数量
     */
    public Result findWarnSimilarOfATCCount(String sectorName){
        Result warnSimilarOfATC = this.findWarnSimilarOfATC(sectorName);
        Object result = warnSimilarOfATC.getResult();
        List<?> result1 = (List<?>) result;
        int size = result1.size();
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS,size);
    }
}