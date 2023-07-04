package com.qrsoft.etl.dao;

import com.qrsoft.etl.dao.entity.ATCEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ATCDao  extends IBaseDao {


    private final static Logger logger = LoggerFactory.getLogger(ATCDao.class);

    /**
     * 根据航班号查询是否该扇区有该航班的统计数据存在
     * @param acid 航班号
     * @return
     */
    public boolean isExistThisAtc(String acid){
        String sql = " SELECT COUNT(*) from atc_number where ACID='"+acid+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    /**
     *  修改指定航班的扇区统计信息
     * @param atc 扇区类
     */
    public void updateAnAtcMsg(ATCEntity atc) {
        String sql = "update atc_number set PLAN_SECTOR_NAME='"+atc.getPlanSectorName()+"',ATC_TIME='"+atc.getAtcTime()+"',EXECUTE_DATE='"+atc.getExecuteDate()+"' where ACID='"+atc.getAcId()+"'; ";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("修改指定航班的扇区统计信息失败! "+atc.getAcId());
            e.printStackTrace();
        }
    }

    /**
     *  创建新航班扇区的统计信息
     *@param atc 扇区类
     */
    public void createAnAtcMsg(ATCEntity atc) {
        String sql = "insert into atc_number (ACID,ATC_TIME,EXECUTE_DATE,PLAN_SECTOR_NAME) values ('"+atc.getAcId()+"','"+atc.getAtcTime()+"','"+atc.getExecuteDate()+"','"+atc.getPlanSectorName()+"');";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("创建航班的扇区的统计信息失败！"+atc.getAcId());
            e.printStackTrace();
        }
    }

}

