package com.qrsoft.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qrsoft.common.Result;
import com.qrsoft.common.ResultConstants;
import com.qrsoft.entity.Company;
import com.qrsoft.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends ServiceImpl<CompanyMapper, Company> {
    /**
     * 查询指挥航空公司航班数，和航班延误数
     */
    public Result findCompanyDelay() {
        List<Company> companyDelay = baseMapper.findCompanyDelay();
        for (Company c : companyDelay) {
            double dcount = Double.parseDouble(c.getDelayCount());
            double count = Double.parseDouble(c.getCount());
            Double rest = dcount / count * 100;
            c.setDelayCount(String.format("%.2f", rest));
        }
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS, companyDelay);
    }

    /**
     * 查询指挥航空公司占比
     */
    public Result findCompanyByCompanyAll() {
        List<Company> companyDelay = baseMapper.findCompanyDelay();
        double sum = 0;
        for (Company c : companyDelay) {
            double count = Double.parseDouble(c.getCount());
            sum+=count;
        }
        for (Company c : companyDelay) {
            double count = Double.parseDouble(c.getCount());
            c.setCompanyCount((count!=0)?sum/count:0);
        }
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS, companyDelay);
    }

    /**
     * 机场负荷统计
     */
    public Result findAirportCount(){
        List<Company> companyDelay = baseMapper.findCompanyDelay();
        double sum = 0;
        for (Company c : companyDelay) {
            double count = Double.parseDouble(c.getCount());
            sum+=count;
        }
        for (Company c : companyDelay) {
            double count = Double.parseDouble(c.getCount());
            c.setCompanyCount((count!=0)?sum/count:0);
        }
        return new Result(ResultConstants.SUCCESS, ResultConstants.C_SUCCESS, companyDelay);
    }
}
