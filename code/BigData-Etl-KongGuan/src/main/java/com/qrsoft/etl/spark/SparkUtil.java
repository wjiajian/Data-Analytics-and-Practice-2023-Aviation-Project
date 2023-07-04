package com.qrsoft.etl.spark;

import com.alibaba.fastjson.JSONObject;
import com.qrsoft.etl.dao.*;
import com.qrsoft.etl.dao.entity.*;
import com.qrsoft.etl.util.MapManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SparkUtil {
    private final static Logger logger = LoggerFactory.getLogger(SparkUtil.class);
    // 初始化扇区
    private static Double[] sectionG = {38.716066,42.297914,114.648477,128.759203};
    private static Double[] sectionK = {35.519796,38.716066,114.648477,128.759203};
    private static Double[] sectionE = {32.519796,35.519796,114.648477,128.759203};

    /**
     * 业务处理
     * @param jsonObject  机场起降数据
     * @throws Exception
     */
    public void TaskPlanData(JSONObject jsonObject) throws Exception {
        //起飞机场
        String adep = jsonObject.getString("ADEP");
        //降落机场
        String ades = jsonObject.getString("ADES");
        //操作数据库，统计和更新机场航班数
        operationDB(adep);
        operationDB(ades);
        //航班号
        String acid = jsonObject.getString("ACID");
        //操作数据库，统计和更新航线信息
        operationDBBOLT(adep, ades, acid);
    }
    /**
     * 操作数据库（对航班起降数进行统计或更新）
     * @param code “起飞机场”或“降落机场”
     */
    public void operationDB(String code) {
        //根据机场代码获取目前数据库中已存在的航班数
        PlanDataDao pDao = new PlanDataDao();
        boolean bool;
        try {
            bool = pDao.isExistThisAir(code);
            if (bool) {
                //存在，在原来基础上+1,修改数据库中该机场的航班数
                pDao.updateAnAirMsg(code);
            } else {
                //不存在，在统计表中创建该机场的航班数（默认为1）
                pDao.createAnAirMsg(code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 操作数据库（对航线进行统计或更新）
     * @param adep 起飞机场
     * @param ades 降落机场
     * @param acid 航班号
     */
    public void operationDBBOLT(String adep, String ades, String acid) {
        boolean bool;
        PlanDataDao pDao = new PlanDataDao();
        if (pDao.isDomesticThisLine(adep) && pDao.isDomesticThisLine(ades)) {
            bool = pDao.isExistThisLine(acid);
            if (bool) {
                pDao.updateAnLineMsg(acid);
            } else {
                pDao.createAnLineMsg(acid, adep, ades);
            }
        }
    }
    // ... ...
    // ... 其他方法。因为当前要实现的是“机场起降数据”，所以其他可以只有方法体，没有方法实现及返回值。 ...
    // ... ...
    /**
     * 业务处理
     * @param strs    航迹数据
     */
    public void TaskRadarStr(String strs){
        System.out.println(strs);
        String[] str = strs.split(",");
        logger.info(str.toString());
        try {
            //js.getString("SEND_RADAR_TIME");
            //MultiRadar mr = new MultiRadar(str[1],str[14],str[13],str[0],str[19],str[17],str[20],str[21],str[11],str[10],str[9],str[15],str[16],str[12],str[5],str[8],str[6],str[18],str[7],str[4]);

            //判断是哪个扇区 sectionG sectionK sectionE

            String sectionVal = "";

            MapManager mapMan = new MapManager();
            double lat = Double.valueOf(str[8]);
            double lng = Double.valueOf(str[9]);

            if(mapMan.isInRectangleArea(lat,lng,sectionG[0],sectionG[1],sectionG[2],sectionG[3])){
                sectionVal = "G";
            }else if(mapMan.isInRectangleArea(lat,lng,sectionK[0],sectionK[1],sectionK[2],sectionK[3])){
                sectionVal = "K";
            }else if(mapMan.isInRectangleArea(lat,lng,sectionE[0],sectionE[1],sectionE[2],sectionE[3])){
                sectionVal = "E";
            };
            System.out.println("=========================================================");
            System.out.println("========================"+sectionVal+"=================================");
            System.out.println("=========================================================");
            MultiRadarEntity mr = new MultiRadarEntity();
            //MultiRadar mr = new MultiRadar(str[1],str[12],str[11],str[0],str[17],str[15],str[18],str[19],str[9],str[8],str[7],str[13],str[14],str[10],str[3],str[6],str[4],str[16],str[5],str[2],sectionVal);
            mr.setAcid(str[0]);
            mr.setAreaSource(str[1]);
            mr.setClimbordownSpeed(str[2]);
            mr.setDirection(str[3]);
            mr.setFcu(str[4]);
            mr.setFlyStatus(str[5]);
            mr.setRadarCFL(str[6]);
            mr.setRadarHeight(str[7]);
            mr.setRadarLatitude(str[8]);
            mr.setRadarLongTitude(str[9]);
            mr.setRadarSpeed(str[10]);
            mr.setRadarType(str[11]);
            mr.setSendRadarTime(str[12]);
            mr.setSpeedX(str[13]);
            mr.setSpeedY(str[14]);
            mr.setSsrCode(str[15]);
            mr.setTime(str[16]);
            mr.setTrackNumber(str[17]);
            mr.setZhiJiaoX(str[18]);
            mr.setZhiJiaoY(str[19]);
            mr.setSection(sectionVal);

            //根据航班号，查询是否已经开始对该航迹进行统计
            MultiRadarDao dao = new MultiRadarDao();
            boolean bool = dao.isExistThisRadar(mr.getAcid());
            System.out.println("-_-"+mr);
            if(bool) {
                //存在，修改数据库中该航迹
                dao.updateAnRadarMsg(mr);
                System.out.println("-_-updateAnRadarMsg"+mr);
            }else{
                //尚未进行统计 创建一个统计信息
                dao.createAnRadarMsg(mr);
                System.out.println("-_-createAnRadarMsg"+mr);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info(" MultiRadar错误数据： [{}]", strs);
        }
    }

    /**
     * 业务处理（相似报警数据）
     * @param jsonObject 相似报警数据
     * @throws Exception
     */
    public void TaskWarnsimilar(JSONObject jsonObject) throws Exception {
        //设置清洗方式
        WarnSimilarHistoryDao wSHDao = new WarnSimilarHistoryDao();
        WarnSimilarHistoryEntity warn = new WarnSimilarHistoryEntity();
        String gjId = jsonObject.getString("GJ_ID");
        boolean bool = wSHDao.isExistThisWarnSimilar(gjId);
        if (bool) {
            //存在，在原来基础上+1，修改数据库中该相似告警数量
            wSHDao.updateAnWarnSimilarMsg(gjId);
        } else {
            //尚未进行统计，创建一个统计信息
            warn.setGjType(jsonObject.getString("GJ_TYPE"));
            warn.setGjId(jsonObject.getString("GJ_ID"));
            warn.setGjMsgType(jsonObject.getString("GJ_MSG_TYPE"));
            warn.setGjNum(jsonObject.getString("GJ_NUM"));
            warn.setGjTrackNum(jsonObject.getString("GJ_TRACK_NUM"));
            warn.setGjSector(jsonObject.getString("GJ_SECTOR"));
            warn.setGjAcid(jsonObject.getString("GJ_ACID"));
            warn.setGjStatus(jsonObject.getString("GJ_STATUS"));
            warn.setGjCity(jsonObject.getString("GJ_CITY"));
            warn.setGjDate(jsonObject.getString("GJ_DATE"));
            warn.setCount(1);
            wSHDao.createAnWarnSimilarMsg(warn);
        }
    }

    /***
     * 业务处理
     * @param jsonObject 空管员数据
     * @throws Exception
     */
    public void TaskAtcduty(JSONObject jsonObject) throws Exception {
        ATCDutyDao ATCDut = new ATCDutyDao();
        //设置清洗方式

//                空管员任务
        String dutyPerson = jsonObject.getString("ATC_DUTY_PERSON");
        //根据空管员名称，查询是否已经开始对该空管员进行统计
        boolean bool = ATCDut.isExistThisAtcDuty(dutyPerson);
        if (bool) {
            ATCDut.updateAnAtcDutyMsg(dutyPerson);
        } else {
            //尚未进行统计 创建一个统计信息
            ATCDutyEntity atcDuty = new ATCDutyEntity(jsonObject.getString("SEND_TIME"),jsonObject.getString("AREA_SOURCE"),jsonObject.getString("ATC_DUTY_POSITION"),jsonObject.getString("ATC_DUTY_PERSON"),jsonObject.getString("ATC_DUTY_SECTOR"),1);
            ATCDut.createAnAtcDutyMsg(atcDuty);
        }
    }

    /**
     * 处理报警数据
     * @param jsonObject    报警数据
     * @throws Exception
     */
    public void TaskWarnfLight(JSONObject jsonObject) throws Exception {
        //根据报警代码，查询是否已经开始对该报警进行统计
        WarFlightHistoryDao warFDao = new WarFlightHistoryDao();
        //设置清洗方式
        String gjId = jsonObject.getString("GJ_ID");
        String gjSector = jsonObject.getString("GJ_SECTOR");

        boolean bool = warFDao.isExistThisWarn(gjId);
        if (gjSector != null && !"".equals(gjSector)) {
            if (bool) {
                //存在，在原来基础上+1，修改数据库中该警告数量
                warFDao.updateAnWarnMsg(gjId);
            } else {
                //尚未进行统计 创建一个统计信息
                WarnFlightHistoryEntity warn = new WarnFlightHistoryEntity();
                warn.setGjType(jsonObject.getString("GJ_TYPE"));
                warn.setGjId(jsonObject.getString("GJ_ID"));
                warn.setGjMsgType(jsonObject.getString("GJ_MSG_TYPE"));
                warn.setGjTrackNum1(jsonObject.getString("GJ_TRACK_NUM1"));
                warn.setGjTrackNum2(jsonObject.getString("GJ_TRACK_NUM2"));
                warn.setGjDistinct(jsonObject.getString("GJ_DISTINCT"));
                warn.setGjRadian(jsonObject.getString("GJ_RADIAN"));
                warn.setGjName(jsonObject.getString("GJ_NAME"));
                warn.setGjDistinctBz(jsonObject.getString("GJ_DISTINCT_BZ"));
                warn.setGjCity(jsonObject.getString("GJ_CITY"));
                warn.setGjDate(jsonObject.getString("GJ_DATE"));
                warn.setGjSector(jsonObject.getString("GJ_SECTOR"));
                warn.setCount(1);
                warFDao.createAnWarnMsg(warn);
            }

        }
    }

    /**
     * 业务处理  航空公司数据
     * @param jsonObject 航空公司数据
     * @throws Exception
     */
    public void TaskAftn(JSONObject jsonObject) throws Exception {
        AFTNDao aftnDao = new AFTNDao();
        //********************
        AFTNEntity aftn = new AFTNEntity();
        aftn.setPlanHeight(jsonObject.getString("PLAN_HEIGHT"));
        aftn.setExecuteDate(jsonObject.getString("EXECUTE_DATE"));
        aftn.setAreaSource(jsonObject.getString("AREA_SOURCE"));
        aftn.setAdes(jsonObject.getString("ADES"));
        aftn.setAdep(jsonObject.getString("ADEP"));
        aftn.setAcid(jsonObject.getString("ACID"));
        aftnDao.insertAftn(aftn);

        //*********************
        //获取计划起飞时间
        String stod = null;
        //获取实际起飞时间
        String atot = null;
        try {
            stod = jsonObject.getString("STOD");
            atot = jsonObject.getString("ETOT");
        } catch (Exception e) {
            System.out.println(" 航空公司数据错误-没有起降时间");
        }
        try {
            if (atot != null && !atot.equals("") && stod != null && !stod.equals("")) {
                //航班号
                String acid = jsonObject.getString("ACID");
                Integer delayCount = 0;
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
                Date parseStoa = sf.parse(stod);
                Date parseAldt = sf.parse(atot);

                long delayTime = parseAldt.getTime() - parseStoa.getTime();

                //延误30分钟即航班延误
                if (delayTime > 30 * 60 * 1000) {
                    delayCount = 1;
                }
                //根据航班号，查询是否已经开始对该航班号进行统计
                String rowKey = jsonObject.getString("rowKey");
                //航空公司

                String companyCode = rowKey.substring(0, 3);
                boolean existThisCompany = aftnDao.isExistThisCompany(companyCode);
                boolean bool = aftnDao.isExistThisACID(acid);
                if (existThisCompany) {
                    CompanyEntity company = new CompanyEntity();
                    if (!bool) {
                        //尚未进行统计 创建一个统计信息
                        company.setAcid(acid);
                        company.setCompanyCode3(companyCode);
                        company.setDelayCount(delayCount);
                        aftnDao.createAnACIDMsg(company);
                    } else {
                        //更新该航班延误分析统计
                        company.setAcid(acid);
                        company.setDelayCount(delayCount);
                        aftnDao.updateAnAcidDelayMsg(company);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" 航空公司数据错误");
        }
    }

    /**
     * 业务处理
     * @param jsonObject 扇区数据
     * @throws Exception
     */
    public void TaskAtc(JSONObject jsonObject) throws Exception {
        ATCDao atcD = new ATCDao();
        ATCEntity atc = new ATCEntity();
        //设置清洗方式
        String sectorName = null;
        try {
            sectorName = jsonObject.getString("PLAN_SECTOR_NAME");
        } catch (Exception e) {
            //logger.info(" ATC无扇区数据： [{}]");
            System.out.println("ATC无扇区数据");
        }
        try {
            //根据扇区，查询是否已经开始对该扇区进行统计
            String ACID = jsonObject.getString("ACID");
            if(sectorName!=null&&!sectorName.equals("")){
                boolean bool = atcD.isExistThisAtc(ACID);

                atc.setAcId(jsonObject.getString("ACID"));
                atc.setAtcTime(jsonObject.getString("ATC_TIME"));
                atc.setExecuteDate(jsonObject.getString("EXECUTE_DATE"));
                atc.setPlanSectorName(jsonObject.getString("PLAN_SECTOR_NAME"));

                if (bool) {
                    //存在，在原来基础上+1，修改数据库中该航迹数量
                    atcD.updateAnAtcMsg(atc);
                } else {
                    //尚未进行统计  创建一个统计信息
                    atcD.createAnAtcMsg(atc);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 业务处理     扇区通话饱和度数据
     * @param jsonObject 扇区通话饱和度数据
     * @throws Exception
     */
    public void TaskCallsaturation(JSONObject jsonObject) throws Exception {
        CallSaturationDao callDao = new CallSaturationDao();
        CallSaturationEntity callSaturation = new CallSaturationEntity();
        //设置清洗方式
        callSaturation.setSendTime(jsonObject.getString("SEND_TIME"));
        callSaturation.setAreaSource(jsonObject.getString("AREA_SOURCE"));
        callSaturation.setThbhTime(jsonObject.getString("THBH_TIME"));
        callSaturation.setThbhSect(jsonObject.getString("THBH_SECT"));
        callSaturation.setThbhValue(jsonObject.getString("THBH_VALUE"));

        //根据扇区号，查询是否已经开始对该扇区进行统计

        boolean bool = callDao.isExistThiscallSaturation(callSaturation.getThbhSect());
        if (bool) {
            //存在，修改数据库中该扇区的通话饱和度
            callDao.updateAncallSaturationMsg(callSaturation);
        } else {
            //尚未进行统计 创建一个统计信息
            callDao.createAncallSaturationMsg(callSaturation);
        }
    }
}
