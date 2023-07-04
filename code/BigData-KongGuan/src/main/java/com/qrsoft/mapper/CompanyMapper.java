package com.qrsoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qrsoft.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
    @Select("SELECT company_name,COUNT(*) as count,SUM(delay_count) as delayCount FROM company_number GROUP BY company_name ORDER BY COUNT(*) DESC LIMIT 19;")
    List<Company> findCompanyDelay();
}