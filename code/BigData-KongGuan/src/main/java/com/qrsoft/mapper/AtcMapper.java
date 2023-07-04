package com.qrsoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qrsoft.entity.Atc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AtcMapper extends BaseMapper<Atc> {
    @Select("select PLAN_SECTOR_NAME,COUNT(*) as count from atc_number GROUP BY PLAN_SECTOR_NAME;")
    List<Atc> findSectorSortie();

    @Select("select EXECUTE_DATE from atc_number group by EXECUTE_DATE order by EXECUTE_DATE desc limit 19;")
    List<String> findATCTime();

    @Select("select PLAN_SECTOR_NAME,count(*) as count from atc_number where EXECUTE_DATE = #{executeTime} and PLAN_SECTOR_NAME = #{sectorName}")
    Atc findATCTime2(String executeTime,String sectorName);
}