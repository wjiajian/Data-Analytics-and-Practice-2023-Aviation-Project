package com.qrsoft.etl.dao;

import com.qrsoft.etl.dao.entity.WarnSimilarHistoryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WarnSimilarHistoryDao extends IBaseDao {

    private final static Logger logger = LoggerFactory.getLogger(WarnSimilarHistoryDao.class);

    /**
     * 根据相似告警ID查询是否有该相似告警存在
     * @param gjId 相似告警ID
     * @return
     */
    public boolean isExistThisWarnSimilar(String gjId){
        String sql = " SELECT COUNT(*) from warnsimilarhistory_number where gj_id='"+gjId+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    /**
     *  修改相似指定告警的统计信息
     * @param gjId 告警ID
     */
    public void updateAnWarnSimilarMsg(String gjId) {
        String sql = "update warnsimilarhistory_number set count=count+'1' where gj_id='"+gjId+"'; ";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("修改指定相似告警统计信息（统计数在原来基础上+1）失败! "+gjId);
            e.printStackTrace();
        }
    }

    /**
     *  创建新相似告警的统计信息
     *@param warn 相似告警类
     */
    public void createAnWarnSimilarMsg(WarnSimilarHistoryEntity warn) {
        String sql = "insert into warnsimilarhistory_number (gj_type,gj_id,gj_msg_type,gj_num,gj_track_num,gj_sector,gj_acid,gj_status,gj_city,gj_date,count) values ('"+warn.getGjType()+"','"+warn.getGjId()+"','"+warn.getGjMsgType()+"','"+warn.getGjNum()+"','"+warn.getGjTrackNum()+"','"+warn.getGjSector()+"','"+warn.getGjAcid()+"','"+warn.getGjStatus()+"','"+warn.getGjCity()+"','"+warn.getGjDate()+"',"+warn.getCount()+");";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("创建新相似告警的统计信息失败！"+warn.getGjId());
            e.printStackTrace();
        }
    }
}
