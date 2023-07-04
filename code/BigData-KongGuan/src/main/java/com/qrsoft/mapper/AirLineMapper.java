package com.qrsoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qrsoft.entity.AirLine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface AirLineMapper extends BaseMapper<AirLine> {

    @Select("SELECT adepname,adesname,adeplong,adeplat,adeslong,adeslat,COUNT(*) as count FROM airline_number WHERE adepcode = 'ZSQD' GROUP BY adesname ORDER BY COUNT(*) DESC LIMIT 10;")
    List<AirLine> findBylimit();

    @Select("SELECT COUNT(*) FROM (SELECT * FROM airline_number WHERE adepcode = 'ZSQD' GROUP BY adescode) line;")
    Integer findLineCount();
}