package com.qrsoft.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qrsoft.common.Result;
import com.qrsoft.common.ResultConstants;
import com.qrsoft.entity.Atc;
import com.qrsoft.entity.MultiRadar;
import com.qrsoft.mapper.AtcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AtcService extends ServiceImpl<AtcMapper, Atc> {
    @Autowired
    private MultiRadarService multiRadarService;

    /**
     * 查询所有扇区航班架次
     */
    public Result findSectorSortie() {
        List<Atc> sectorSortie = baseMapper.findSectorSortie();
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS, sectorSortie);
    }

    /**
     * 根据扇区号查询架次
     * @param planSectorName
     */
    public Result findLocusCount(String planSectorName) {
        QueryWrapper<MultiRadar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("section",planSectorName);
        int count = multiRadarService.count(queryWrapper);
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS, count);
    }

    /**
     * 扇区架次数动态统计(饼状图)
     */
    public Result findATCTime() {
        List<String> sectorName = new ArrayList<>();
        sectorName.add("K");
        sectorName.add("S");
        sectorName.add("E");
        sectorName.add("P");
        sectorName.add("G");
        List<String> executeTime = baseMapper.findATCTime();

        List list = new ArrayList();
        for (int i = 0; executeTime.size() > i; i++) {
            ArrayList<Object> objects = new ArrayList<>();
            for (int j = 0; sectorName.size() > j; j++) {
                Atc atcTime2 = baseMapper.findATCTime2(executeTime.get(i), sectorName.get(j));
                HashMap<String, Object> map = new HashMap<>();
                if (atcTime2.getPlanSectorName() != null) {
                    map.put(atcTime2.getPlanSectorName(), atcTime2.getCount());
                }else {
                    map.put(sectorName.get(j),0);
                }
                objects.add(map);
            }
            list.add(objects);
        }
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS, list);
    }
}
