package com.qrsoft.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Title: DateUtils.java Description: 日期工具类
 */
public class DateUtils {

    public static String myDateUtils(String time){
        if(time.equals("20181231")){
            return "20180101";
        }
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        String newDate = time;
        try {
            Date parse = sd.parse(time);
            long newTime = parse.getTime()+(1000*60*60*24);
            Date newDate1 = new Date(newTime);
            newDate = sd.format(newDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
