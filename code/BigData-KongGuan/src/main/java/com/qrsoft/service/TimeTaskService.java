package com.qrsoft.service;

import com.alibaba.fastjson.JSON;
import com.qrsoft.common.Constants;
import com.qrsoft.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TimeTaskService {

    //本次查询的最后一个rowKey
    //kg_PlanData计划数据
    private static byte[] planDataLastRowKey = null;
    //Kg_WarnFlightHistory航班指令告警数据
    private static byte[] warnFlightLastRowKey = null;
    //Kg_ATCDutyInfo管制值班人员数据
    private static byte[] aTCDutyLastRowKey = null;
    //Kg_WarnSimilarHistory相似航班号告警数据
    private static byte[] warnSimilarLastRowKey = null;
    //kg_AFTN报文数据
    private static byte[] aFTNLastRowKey = null;
    //kg_ATC报文数据
    private static byte[] aTCLastRowKey = null;
    //kg_callSaturation扇区通话饱和度数据
    private static byte[] callSaturationLastRowKey = null;


    //kg_ATC扇区数据 计时
    private static String atcDataTime = "20180101";
    //kg_AFTN报文数据 计时
    private static String aftnDataTime = "20180101";
    //kg_callSaturation扇区通话饱和度数据 计时
    private static String callDataTime = "20180101";
    //kg_PlanData计划数据 计时
    private static String planDataTime = "20180101";
    //Kg_WarnFlightHistory航班指令告警数据 计时
    private static String warnFlightDataTime = "20180101";
    //Kg_WarnSimilarHistory相似航班号告警数据 计时
    private static String warnSimilarDataTime = "20180101";
    //Kg_ATCDuty 管制员数据 计时
    private static String atcDutyDataTime = "20180101";

    //每次查询的数据量
    private static int selectLimit = 30;

    private static byte[] getDateAndDepositInKafka(String topic, String tableName,
                                                   byte[] lastRowKey, String date, String family, String column) throws SQLException {
        // 从hbase中取出数据
        List<Filter> rowFilters = new ArrayList<Filter>();
        FilterList filterList = new FilterList(rowFilters);
        HBasePageModel pageModel = new HBasePageModel(selectLimit + 1);

        if (date != null) {
            Filter filter = HQueryFilterUtil.newSubStringFilter(family, column, date);
            filterList.addFilter(filter);
        }

        HBasePageModel hbasePageModel = HBaseUtils.scanResultByPageFilter(
                tableName, lastRowKey, null, filterList, 0, pageModel);
        // 获取本次查询的最后一个rowKey
        int dataSize = hbasePageModel.getList().size();
        if (dataSize > 0) {
            Map<String, String> map = hbasePageModel.getList().get(dataSize - 1);
            String rowkey = map.get("rowKey");
            lastRowKey = rowkey.getBytes();
            // 把每一条数据放入Kafka
            for (int i = 0; i < hbasePageModel.getList().size() - 1; i++) {
                // System.out.println(JSONArray.fromObject(hbasePageModel.getList().get(i)).toString());
                KafkaUtils.SendMessage(topic, JSON.toJSONString(hbasePageModel.getList().get(i)));
                System.out.println("============" + JSON.toJSONString(hbasePageModel.getList().get(i)) + "==============");
            }
            System.out.println(tableName + "此批次最后一个rowkey：" + rowkey);
        }

        System.out.println(dataSize + "===================================" + selectLimit);
        if (dataSize < selectLimit) {
            if (topic.equals(Constants.TASK_ATC)) {
                atcDataTime = DateUtils.myDateUtils(date);
                System.out.println(Constants.TASK_ATC + "更新数据时间为" + atcDataTime);
            } else if (topic.equals(Constants.TASK_AFTN)) {
                aftnDataTime = DateUtils.myDateUtils(date);
                System.out.println(Constants.TASK_AFTN + "更新数据时间为" + aftnDataTime);
            } else if (topic.equals(Constants.TASK_PlANDATA)) {
                planDataTime = DateUtils.myDateUtils(date);
                System.out.println(Constants.TASK_PlANDATA + "更新数据时间为" + planDataTime);
            } else if (topic.equals(Constants.TASK_WARNFLIGHT)) {
                warnFlightDataTime = DateUtils.myDateUtils(date);
                System.out.println(Constants.TASK_WARNFLIGHT + "更新数据时间为" + warnFlightDataTime);
            } else if (topic.equals(Constants.TASK_WARNSIMILAR)) {
                warnSimilarDataTime = DateUtils.myDateUtils(date);
                System.out.println(Constants.TASK_WARNSIMILAR + "更新数据时间为" + warnSimilarDataTime);
            } else if (topic.equals(Constants.TASK_CALLSATURATION)) {
                callDataTime = DateUtils.myDateUtils(date);
                System.out.println(Constants.TASK_CALLSATURATION + "更新数据时间为" + callDataTime);
            } else if (topic.equals(Constants.TASK_ATCDUTY)) {
                atcDutyDataTime = DateUtils.myDateUtils(date);
                System.out.println(Constants.TASK_ATCDUTY + "更新数据时间为" + atcDutyDataTime);
            }
        }
        return lastRowKey;
    }

    // … timingGetData方法 …
    @Scheduled(cron = "*/30 * * * * *")
    public void timingGetData() {
        System.out.println("定时任务启动获取数据");
        try {
            planDataLastRowKey = getDateAndDepositInKafka(Constants.TASK_PlANDATA, Constants.TABLE_PlANDATA, planDataLastRowKey, planDataTime, Constants.FAMILY_PlANDATA, Constants.COLUMN_PlANDATA);
            warnFlightLastRowKey = getDateAndDepositInKafka(Constants.TASK_WARNFLIGHT, Constants.TABLE_WARNFLIGHT, warnFlightLastRowKey, warnFlightDataTime, Constants.FAMILY_WARNFLIGHT, Constants.COLUMN_WARNFLIGHT);
            aTCDutyLastRowKey = getDateAndDepositInKafka(Constants.TASK_ATCDUTY, Constants.TABLE_ATCDUTY, aTCDutyLastRowKey, atcDutyDataTime, Constants.FAMILY_ATCDUTY, Constants.COLUMN_ATCDUTY);
            warnSimilarLastRowKey = getDateAndDepositInKafka(Constants.TASK_WARNSIMILAR, Constants.TABLE_WARNSIMILAR, warnSimilarLastRowKey, warnSimilarDataTime, Constants.FAMILY_WARNSIMILAR, Constants.COLUMN_WARNSIMILAR);
            aFTNLastRowKey = getDateAndDepositInKafka(Constants.TASK_AFTN, Constants.TABLE_AFTN, aFTNLastRowKey, aftnDataTime, Constants.FAMILY_AFTN, Constants.COLUMN_AFTN);
            aTCLastRowKey = getDateAndDepositInKafka(Constants.TASK_ATC, Constants.TABLE_ATC, aTCLastRowKey, atcDataTime, Constants.FAMILY_ATC, Constants.COLUMN_ATC);
            callSaturationLastRowKey = getDateAndDepositInKafka(Constants.TASK_CALLSATURATION, Constants.TABLE_CALLSATURATION, callSaturationLastRowKey, callDataTime, Constants.FAMILY_CALLSATURATION, Constants.COLUMN_CALLSATURATION);
            // 注意：
            //1）“实时飞行数据”对应的Topic，由于直接从HBase读取实时飞行数据推送到Kafka后，在使用时会因为实时飞行数据的时间隔太短，导致在地图上显示飞行状态和位置时，移动的不明显；
            //2）为了在演示的时候提高体验度，所以将下面一行代码注释掉，而这部分功能单独写了一个TimeTask2Service.java的类；
            //3）在TimeTask2Service.java的类中，使用的实时飞行数据是从HBase中读取的已经处理过的保存到本地的数据，实时飞行数据的时间间隔变大，在使用该数据时能明显感觉到飞机在移动；
            //radarLastRowKey=getDateAndDepositInKafka(Constants.TASK_RADAR,Constants.TABLE_RADAR,radarLastRowKey,multiRadarDataTime,Constants.FAMILY_RADAR,Constants.COLUMN_RADAR);

        } catch (SQLException e) {
            e.printStackTrace();
            log.info("获取数据失败！", e.getMessage());
        }
    }
}