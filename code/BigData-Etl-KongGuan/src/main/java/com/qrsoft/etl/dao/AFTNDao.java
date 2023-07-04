package com.qrsoft.etl.dao;
import com.qrsoft.etl.dao.entity.AFTNEntity;
import com.qrsoft.etl.dao.entity.CompanyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AFTNDao extends IBaseDao {

    private final static Logger logger = LoggerFactory.getLogger(AFTNDao.class);

    /**
     * 查询是否有该航班分析统计信息存在
     * @param acid 航班号
     * @return
     */
    public boolean isExistThisACID(String acid){
        String sql = " SELECT COUNT(*) from company_number where acid='"+acid+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    /**
     * 查询是否有该航空公司信息存在
     * @param code3
     * @return
     */
    public boolean isExistThisCompany(String code3){
        String sql = " SELECT count(*) FROM kg_airlinecompany WHERE AIRCOMPANY_CODE3 = '"+code3+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    /**
     *创建新航班的统计信息
     *@param company 航空公司类
     */
    public void createAnACIDMsg(CompanyEntity company) {
        String sql = "insert into company_number (acid,company_code3,company_name,delay_count) values ('"+company.getAcid()+"','"+company.getCompanyCode3()+"',(select AIRCOMPANY_CNAME from kg_airlinecompany where AIRCOMPANY_CODE3 = '"+company.getCompanyCode3()+"'),"+company.getDelayCount()+");";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("创建新航空公司的统计信息失败！"+company.getCompanyCode3());
            e.printStackTrace();
        }
    }

    /**
     *  修改指定航班的统计信息（统计延误数在原来基础上+1）
     * @param company 航空公司类
     */
    public void updateAnAcidDelayMsg(CompanyEntity company) {
        String sql = "update company_number set delay_count="+company.getDelayCount()+" where company_code3='"+company.getCompanyCode3()+"'; ";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("修改指定航空公司的延误统计信息（统计延误数在原来基础上+1）失败！  " + company.getCompanyCode3());
            e.printStackTrace();
        }
    }

    /**
     *插入数据
     */
    public void insertAftn(AFTNEntity aftn) {
        String sql = "insert into kg_aftn (acid,plan_height,execute_date,adep,ades) values ('"+aftn.getAcid()+"','"+aftn.getPlanHeight()+"','"+aftn.getExecuteDate()+"','"+aftn.getAdep()+"','"+aftn.getAdes()+"')";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("Aftn 数据插入成功");
            e.printStackTrace();
        }
    }
}
