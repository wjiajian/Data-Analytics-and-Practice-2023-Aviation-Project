package com.qrsoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qrsoft.entity.Callsaturation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CallsaturationMapper extends BaseMapper<Callsaturation> {

    @Select("select THBH_SECT,THBH_VALUE from callsaturation_number group by THBH_SECT;")
    List<Callsaturation> findCallSaturation();
}
