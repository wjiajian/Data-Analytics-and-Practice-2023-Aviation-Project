package com.qrsoft.etl.dao;

import com.qrsoft.etl.dao.entity.MultiRadarEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MultiRadarDao extends IBaseDao {
    private final static Logger logger = LoggerFactory.getLogger(MultiRadarDao.class);
    /**
     * 根据航迹号查询是否有该航线存在
     * @param acid 航班号
     * @return
     */
    public boolean isExistThisRadar(String acid){
        String sql = " SELECT COUNT(*) from multiradar_number  where ACID='"+acid+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    /**
     *  修改指定航迹的统计信息
     * @param multiRadar 综合航迹类
     */
    public void updateAnRadarMsg(MultiRadarEntity multiRadar) {
        String sql = "update multiradar_number set ACID='"+multiRadar.getAcid()+"', " +
                "AREA_SOURCE='"+multiRadar.getAreaSource()+"',"+
                "SEND_RADAR_TIME='"+multiRadar.getSendRadarTime()+"',"+
                "SSR_CODE='"+multiRadar.getSsrCode()+"',"+
                "ZHIJIAO_X='"+multiRadar.getZhiJiaoX()+"',"+
                "ZHIJIAO_Y='"+multiRadar.getZhiJiaoY()+"',"+
                "RADAR_LONGTITUDE='"+multiRadar.getRadarLongTitude()+"',"+
                "RADAR_LATITUDE='"+multiRadar.getRadarLatitude()+"',"+
                "RADAR_HEIGHT='"+multiRadar.getRadarHeight()+"',"+
                "SPEED_X='"+multiRadar.getSpeedX()+"',"+
                "SPEED_Y='"+multiRadar.getSpeedY()+"',"+
                "RADAR_SPEED='"+multiRadar.getRadarSpeed()+"',"+
                "DIRECTION='"+multiRadar.getDirection()+"',"+
                "RADAR_CFL='"+multiRadar.getRadarCFL()+"',"+
                "FCU='"+multiRadar.getFcu()+"',"+
                "TIME='"+multiRadar.getTime()+"',"+
                "FLYSTATUS='"+multiRadar.getFlyStatus()+"',"+
                "CLIMBORDOWN_SPEED='"+multiRadar.getClimbordownSpeed()+"', "+
                "section='"+multiRadar.getSection()+"' "+
                "where ACID='"+multiRadar.getAcid()+"';";
        Object[] params = {};
        try {
            logger.info("修改指定航迹统计信息结果:！"+sql);
            boolean update = this.update(sql, params);
            logger.info("修改指定航迹统计信息结果:"+update);
        } catch (SQLException e) {
            logger.info("修改指定航迹统计信息失败! "+multiRadar.getTrackNumber());
            e.printStackTrace();
        }
    }

    /**
     *  创建新航迹的统计信息
     *@param multiRadar 综合航迹类
     */
    public void createAnRadarMsg(MultiRadarEntity multiRadar) {
        String sql = "insert into multiradar_number (TRACK_NUMBER,AREA_SOURCE,SEND_RADAR_TIME,RADAR_TYPE,ACID,SSR_CODE,ZHIJIAO_X,ZHIJIAO_Y,RADAR_LONGTITUDE,RADAR_LATITUDE,RADAR_HEIGHT,SPEED_X,SPEED_Y,RADAR_SPEED,DIRECTION,RADAR_CFL,FCU,TIME,FLYSTATUS,CLIMBORDOWN_SPEED,section) values" +
                " ('"+multiRadar.getTrackNumber()+"','"+multiRadar.getAreaSource()+"','"+multiRadar.getSendRadarTime()+"','"+multiRadar.getRadarType()+"','"+multiRadar.getAcid()+"','"+multiRadar.getSsrCode()+"','"+multiRadar.getZhiJiaoX()+"','"+multiRadar.getZhiJiaoY()+"','"+multiRadar.getRadarLongTitude()+"','"+multiRadar.getRadarLatitude()+"','"+multiRadar.getRadarHeight()+"','"+multiRadar.getSpeedX()+"','"+multiRadar.getSpeedY()+"','"+multiRadar.getRadarSpeed()+"','"+multiRadar.getDirection()+"','"+multiRadar.getRadarCFL()+"','"+multiRadar.getFcu()+"','"+multiRadar.getTime()+"','"+multiRadar.getFlyStatus()+"','"+multiRadar.getClimbordownSpeed()+"','"+multiRadar.getSection()+"');";
        Object[] params = {};
        try {
            logger.info("创建新航线的统计信息结果sql:！"+sql);
            boolean insert = this.update(sql, params);
            logger.info("创建新航线的统计信息结果:！"+insert);
        } catch (SQLException e) {
            logger.info("创建新航线的统计信息失败！"+multiRadar.getTrackNumber());
            e.printStackTrace();
        }
    }
}
