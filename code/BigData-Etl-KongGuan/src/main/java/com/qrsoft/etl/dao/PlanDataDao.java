package com.qrsoft.etl.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanDataDao extends IBaseDao {
    private final static Logger logger = LoggerFactory.getLogger(PlanDataDao.class);

    public boolean isDomesticThisLine(String code4){
        String sql = " SELECT COUNT(*) from airport_longlat where code4 ='"+code4+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    public boolean isExistThisAir(String code) {
        String sql = " SELECT COUNT(*) from airport_number where flightcode='"+code+"';";
        Object[] params = {};
        ResultSet airRs = this.execute(sql, params);
        return getBool(airRs);
    }

    public void updateAnAirMsg(String code) {
        String sql = "update airport_number set count=count+'1' where flightcode='"+code+"'; ";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("修改指定机场的统计信息（统计数在原来基础上+1）失败！  " + code);
            e.printStackTrace();
        }
    }

    public void createAnAirMsg(String code) {
        String sql = "insert into airport_number (flightcode,cname,count) values ('"+code+"',(select airport_cname from kg_airport where AIRPORT_CODE4 = '"+code+"'),'1');";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("创建新机场的统计信息失败！  " + code);
            e.printStackTrace();
        }
    }

    public boolean isExistThisLine(String acid){
        String sql = " SELECT COUNT(*) from airline_number where acid ='"+acid+"';";
        Object[] params = {};
        ResultSet comRs = this.execute(sql, params);
        return getBool(comRs);
    }

    public void updateAnLineMsg(String acid) {
        String sql = "update airline_number set count=count+1 where acid='"+acid+"';";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("修改指定航线统计信息（统计数在原来基础上+1）失败! 航班号：" + acid);
            e.printStackTrace();
        }
    }

    public void createAnLineMsg(String acid,String aDEP,String aDES) {
        String sql = "insert into airline_number (acid,adepcode,adescode,adepname,adesname,adeplong,adeplat,adeslong,adeslat,count) values ('"+acid+"','"+aDEP+"','"+aDES+"',(select airport_cname from kg_airport where airport_code4 = '"+aDEP+"'),(select airport_cname from kg_airport where airport_code4 = '"+aDES+"'),(select longitude from airport_longlat where code4 = '"+aDEP+"'),(select latitude from airport_longlat where code4 = '"+aDEP+"'),(select longitude from airport_longlat where code4 = '"+aDES+"'),(select latitude from airport_longlat where code4 = '"+aDES+"'),'1') ;";
        Object[] params = {};
        try {
            this.update(sql, params);
        } catch (SQLException e) {
            logger.info("创建新航线的统计信息失败！ 航班号：" + acid);
            e.printStackTrace();
        }
    }
}
