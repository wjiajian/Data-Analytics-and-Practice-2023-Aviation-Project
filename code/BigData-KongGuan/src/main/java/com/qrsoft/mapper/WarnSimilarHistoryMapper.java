package com.qrsoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qrsoft.entity.WarnSimilarHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WarnSimilarHistoryMapper extends BaseMapper<WarnSimilarHistory> {

    @Select("SELECT gj_sector,gj_acid FROM warnsimilarhistory_number ORDER BY id DESC LIMIT 4;")
    List<WarnSimilarHistory> findWarnSimilarHistory();

    @Select("SELECT gj_sector,gj_acid FROM warnsimilarhistory_number where gj_sector = #{sectorName} ORDER BY id DESC LIMIT 4;")
    List<WarnSimilarHistory> findWarnSimilarOfATC(@Param("sectorName") String sectorName);
}
