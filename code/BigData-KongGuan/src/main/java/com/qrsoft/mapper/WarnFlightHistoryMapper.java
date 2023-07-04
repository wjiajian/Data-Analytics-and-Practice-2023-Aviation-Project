package com.qrsoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qrsoft.entity.WarnFlightHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface WarnFlightHistoryMapper extends BaseMapper<WarnFlightHistory> {
    @Select("SELECT gj_sector,COUNT(*) as gjCount FROM warnflighthistory_number GROUP BY gj_sector ORDER BY sum(count) desc LIMIT 11;")
    List<WarnFlightHistory> annualWarningAreaStatistics();

    @Select("select gj_name,count(*) as gjCount from warnflighthistory_number group by gj_name;")
    List<WarnFlightHistory> annualWarningStatisticsByCategory();

    @Select("select gj_type,gj_id,gj_msg_type,gj_track_num1,gj_track_num2,gj_distinct,gj_radian,gj_name,gj_distinct_bz,gj_city,gj_date,gj_acids,gj_num1_long,gj_num1_lat,gj_num2_long,gj_num2_lat from warntp_number;")
    List<HashMap<String,Object>> findWarnTp();

    @Select("select count(*) from multiradar_number where `ACID` IN (#{acid},#{bcid});")
    Integer getWarn(@Param("acid") String acid, @Param("bcid") String bcid);
}