package com.qrsoft.etl.dao;

import com.qrsoft.etl.dao.entity.CallSaturationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CallSaturationDao extends IBaseDao {

    private final static Logger logger = LoggerFactory.getLogger(CallSaturationDao.class);

    /**
     * 根据扇区查询是否该扇区通话饱和度统计数据存在
     * @param setc 扇区号
     * @return
     */
    public boolean isExistThiscallSaturation(String setc){
        String sql = " SELECT COUNT(*) from callsaturation_number where THBH_SECT='"+setc+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    /**
     *  修改指定扇区的通话饱和度统计信息
     * @param callSaturation 扇区通话饱和度类
     */
    public void updateAncallSaturationMsg(CallSaturationEntity callSaturation) {
        String sql = "update callsaturation_number set SEND_TIME='"+callSaturation.getSendTime()+"',AREA_SOURCE='"+callSaturation.getAreaSource()+"',THBH_TIME='"+callSaturation.getThbhTime()+"',THBH_VALUE='"+callSaturation.getThbhValue()+"'  where THBH_SECT='"+callSaturation.getThbhSect()+"'; ";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("修改指定扇区的通话饱和度统计信息失败! "+callSaturation.getThbhSect());
            e.printStackTrace();
        }
    }

    /**
     *  创建新扇区通话饱和度的统计信息
     *@param callSaturation 扇区通话饱和度类
     */
    public void createAncallSaturationMsg(CallSaturationEntity callSaturation) {
        String sql = "insert into callsaturation_number (SEND_TIME,AREA_SOURCE,THBH_TIME,THBH_SECT,THBH_VALUE) values ('"+callSaturation.getSendTime()+"','"+callSaturation.getAreaSource()+"','"+callSaturation.getThbhTime()+"','"+callSaturation.getThbhSect()+"','"+callSaturation.getThbhValue()+"');";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("创建新扇区的通话饱和度的统计信息失败！"+callSaturation.getThbhSect());
            e.printStackTrace();
        }
    }
}
