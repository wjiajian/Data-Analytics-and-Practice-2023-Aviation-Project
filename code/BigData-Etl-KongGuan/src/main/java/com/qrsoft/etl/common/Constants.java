package com.qrsoft.etl.common;

public class Constants {
    //间隔时间（10分钟）
    public final static int INTERVAL_TIME_10MIN = 10*60*1000;
    //间隔时间（5分钟）
    public final static int INTERVAL_TIME_5MIN = 5*60*1000;
    //间隔时间（1分钟）
    public final static int INTERVAL_TIME_1MIN = 60*1000;
    //间隔时间（30秒）
    public final static int INTERVAL_TIME_30SEC = 30*1000;
    //间隔时间（10秒）
    public final static int INTERVAL_TIME_10SEC = 10*1000;
    //间隔时间（10秒）
    public final static int INTERVAL_TIME_5SEC = 5*1000;
    //每分钟读取条数
    public final static int READ_COUNT = 10;

    //kg_airport
    public final static String TABLE_AIRPORT = "kg_airport";

    //kg_airlinecompany
    public final static String TABLE_AIRLINECOMPANY = "kg_airlinecompany";

    //kg_PlanData计划数据
    public final static String TASK_PlANDATA = "task_PlanData";
    public final static String TABLE_PlANDATA = "Kg_PlanData";
    public final static String FAMILY_PlANDATA = "ReportHome";
    public final static String COLUMN_PlANDATA = "EXECUTE_DATE";

    //kg_MultiRadarData综合航迹数据
    public final static String TASK_RADAR = "task_Radar";
    public final static String TABLE_RADAR = "Kg_MultiRadarData";
    public final static String FAMILY_RADAR = "RadarHome";
    public final static String COLUMN_RADAR = "SEND_RADAR_TIME";

    //kg_AFTN报文数据
    public final static String TASK_AFTN = "task_Aftn";
    public final static String TABLE_AFTN = "Kg_AFTN";
    public final static String FAMILY_AFTN = "AFTNHome";
    public final static String COLUMN_AFTN = "EXECUTE_DATE";

    //Kg_ATCDutyInfo管制值班人员数据
    public final static String TASK_ATCDUTY = "task_ATCDuty";
    public final static String TABLE_ATCDUTY = "Kg_ATCDutyInfo";
    public final static String FAMILY_ATCDUTY = "ATCDutyHome";
    public final static String COLUMN_ATCDUTY = "SEND_TIME";

    //Kg_WarnFlightHistory航班指令告警数据
    public final static String TASK_WARNFLIGHT = "task_WarnFlight";
    public final static String TABLE_WARNFLIGHT = "Kg_WarnFlightHistory";
    public final static String FAMILY_WARNFLIGHT = "WarnFlightHome";
    public final static String COLUMN_WARNFLIGHT = "GJ_DATE";

    //Kg_WarnSimilarHistory相似航班号告警数据
    public final static String TASK_WARNSIMILAR = "task_WarnSimilar";
    public final static String TABLE_WARNSIMILAR = "Kg_WarnSimilarHistory";
    public final static String FAMILY_WARNSIMILAR = "WarnSimilarHome";
    public final static String COLUMN_WARNSIMILAR = "GJ_DATE";

    //Kg_ATC扇区信息
    public final static String TASK_ATC = "task_ATC";
    public final static String TABLE_ATC = "Kg_ATC";
    public final static String FAMILY_ATC = "ATCHome";
    public final static String COLUMN_ATC = "EXECUTE_DATE";

    //Kg_CallSaturation 扇区通话饱和度信息
    public final static String TASK_CALLSATURATION = "task_CallSaturation";
    public final static String TABLE_CALLSATURATION = "Kg_CallSaturation";
    public final static String FAMILY_CALLSATURATION = "SaturationHome";
    public final static String COLUMN_CALLSATURATION = "SEND_TIME";
}
