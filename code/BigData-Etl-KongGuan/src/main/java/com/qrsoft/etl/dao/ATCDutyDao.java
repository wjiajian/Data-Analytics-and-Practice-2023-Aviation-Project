package com.qrsoft.etl.dao;

import com.qrsoft.etl.dao.entity.ATCDutyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ATCDutyDao extends IBaseDao{

    private final static Logger logger = LoggerFactory.getLogger(ATCDutyDao.class);

    /**
     * 根据管制员名称查询是否有该管制员存在
     * @param atcDutyPerson 管制员名称
     * @return
     */
    public boolean isExistThisAtcDuty(String atcDutyPerson){
        String sql = " SELECT COUNT(*) from atcduty_number where atc_duty_person='"+atcDutyPerson+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    /**
     *  修改指定管制员的统计信息
     * @param atcDutyPerson 管制员名称
     */
    public void updateAnAtcDutyMsg(String atcDutyPerson) {
        String sql = "update atcduty_number set count=count+'1' where atc_duty_person='"+atcDutyPerson+"'; ";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("修改指定管制员统计信息（统计数在原来基础上+1）失败! "+atcDutyPerson);
            e.printStackTrace();
        }
    }

    /**
     *  创建新管制员的统计信息
     *@param atcDuty 管制员类
     */
    public void createAnAtcDutyMsg(ATCDutyEntity atcDuty) {
        String sql = "insert into atcduty_number (send_time,area_source,atc_duty_position,atc_duty_person,atc_duty_sector,count) values ('"+atcDuty.getSendTime()+"','"+atcDuty.getAreaSource()+"','"+atcDuty.getAtcDutyPosition()+"','"+atcDuty.getAtcDutyPerson()+"','"+atcDuty.getAtcDutySector()+"',"+atcDuty.getCount()+");";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("创建管制员的统计信息失败！"+atcDuty.getAtcDutyPerson());
            e.printStackTrace();
        }
    }
}