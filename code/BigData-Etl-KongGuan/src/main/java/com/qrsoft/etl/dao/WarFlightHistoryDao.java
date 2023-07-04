package com.qrsoft.etl.dao;

import com.qrsoft.etl.dao.entity.WarnFlightHistoryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WarFlightHistoryDao extends IBaseDao {

    private final static Logger logger = LoggerFactory.getLogger(WarFlightHistoryDao.class);

    /**
     * 根据告警ID查询是否有该告警存在
     * @param gjId 告警ID
     * @return
     */
    public boolean isExistThisWarn(String gjId){
        String sql = " SELECT COUNT(*) from warnflighthistory_number where gj_id='"+gjId+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    /**
     *  修改指定告警的统计信息
     * @param gjId 告警ID
     */
    public void updateAnWarnMsg(String gjId) {
        String sql = "update warnflighthistory_number set count=count+'1' where gj_id='"+gjId+"'; ";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("修改指定告警统计信息（统计数在原来基础上+1）失败! "+gjId);
            e.printStackTrace();
        }
    }

    /**
     *  创建新告警的统计信息
     *@param warn 告警类
     */
    public void createAnWarnMsg(WarnFlightHistoryEntity warn) {
        String sql = "insert into warnflighthistory_number (gj_type,gj_id,gj_msg_type,gj_track_num1,gj_track_num2,gj_distinct,gj_radian,gj_name,gj_distinct_bz,gj_city,gj_date,count,gj_sector) values ('"+warn.getGjType()+"','"+warn.getGjId()+"','"+warn.getGjMsgType()+"','"+warn.getGjTrackNum1()+"','"+warn.getGjTrackNum2()+"','"+warn.getGjDistinct()+"','"+warn.getGjRadian()+"','"+warn.getGjName()+"','"+warn.getGjDistinctBz()+"','"+warn.getGjCity()+"','"+warn.getGjDate()+"',"+warn.getCount()+",'"+warn.getGjSector()+"');";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("创建新告警的统计信息失败！"+warn.getGjId());
            e.printStackTrace();
        }
    }

    /**
     * 根据告警ID查询是否有该告警存在
     * @param gjId 告警ID
     * @return
     */
    public boolean isExistThisWarnSpark(String gjId){
        String sql = " SELECT COUNT(*) from warnspark_number where gj_id='"+gjId+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    /**
     *  修改指定告警的统计信息
     * @param gjId 告警ID
     */
    public void updateAnWarnSparkMsg(String gjId) {
        String sql = "update warnspark_number set count=count+'1' where gj_id='"+gjId+"'; ";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("修改指定告警统计信息（统计数在原来基础上+1）失败! "+gjId);
            e.printStackTrace();
        }
    }

    /**
     * 创建新告警的统计信息
     * @param gjid
     * @param num1
     * @param num2
     */
    public void createAnWarnSparkMsg(String gjid,String num1,String num2) {
        String sql = "insert into warnspark_number (gj_id,gj_track_num1,gj_track_num2,count) values ('"+gjid+"','"+num1+"','"+num2+"',"+1+");";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("创建新告警的统计信息失败！"+gjid);
            e.printStackTrace();
        }
    }
}
