package com.qrsoft.service;

import cn.hutool.core.io.file.FileReader;
import com.qrsoft.util.KafkaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
public class TimeTask2Service {
    /**
     * 实时航线飞行，为了模拟数据飞行数据飞行，从历史数据取出文本文件，通过读取每行文件，得到飞行轨迹，每个文本文件代表一架飞机
     */
    public static List<String> str0 = null;
    public static int count0 = 0;
    public static List<String> str1 = null;
    public static int count1 = 0;
    public static List<String> str2 = null;
    public static int count2 = 0;
    public static List<String> str3 = null;
    public static int count3 = 0;
    public static List<String> str4 = null;
    public static int count4 = 0;
    public static List<String> str5 = null;
    public static int count5 = 0;
    public static List<String> str6 = null;
    public static int count6 = 0;
    public static List<String> str7 = null;
    public static int count7 = 0;
    public static List<String> str8 = null;
    public static int count8 = 0;
    public static List<String> str9 = null;
    public static int count9 = 0;
    public static List<String> str10 = null;
    public static int count10 = 0;
    public static List<String> str11 = null;
    public static int count11 = 0;

    /**
     * 获取文件位置，如果是linux服务器发布需要放在换成linux目录 如果是window可以使用data里的数据
     */
    public TimeTask2Service() throws URISyntaxException, IOException, InterruptedException {
        FileReader fileReader0 = new FileReader("/opt/data/part-00000");
        str0 = fileReader0.readLines();
        FileReader fileReader1 = new FileReader("/opt/data/part-00001");
        str1 = fileReader1.readLines();
        FileReader fileReader2 = new FileReader("/opt/data/part-00002");
        str2 = fileReader2.readLines();
        FileReader fileReader3 = new FileReader("/opt/data/part-00003");
        str3 = fileReader3.readLines();
        FileReader fileReader4 = new FileReader("/opt/data/part-00004");
        str4 = fileReader4.readLines();
        FileReader fileReader5 = new FileReader("/opt/data/part-00005");
        str5 = fileReader5.readLines();
        FileReader fileReader6 = new FileReader("/opt/data/part-00006");
        str6 = fileReader6.readLines();
        FileReader fileReader7 = new FileReader("/opt/data/part-00007");
        str7 = fileReader7.readLines();
        FileReader fileReader8 = new FileReader("/opt/data/part-00008");
        str8 = fileReader8.readLines();
        FileReader fileReader9 = new FileReader("/opt/data/part-00009");
        str9 = fileReader9.readLines();
        FileReader fileReader10 = new FileReader("/opt/data/part-00010");
        str10 = fileReader10.readLines();
        FileReader fileReader11= new FileReader("/opt/data/part-00011");
        str11 = fileReader11.readLines();
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void timingGetData() {
        System.out.println("实时轨迹任务");
        if (count0 >= str0.size()) {
            count0 = 0;
            System.out.println("数据归零");
        }
        String s0 = str0.get(count0++);

        if (count1 >= str1.size()) {
            count1 = 0;
            System.out.println("数据归零");
        }
        String s1 = str1.get(count1++);

        if (count2 >= str2.size()) {
            count2 = 0;
            System.out.println("数据归零");
        }
        String s2 = str2.get(count2++);
        if (count3 >= str3.size()) {
            count3 = 0;
            System.out.println("数据归零");
        }
        String s3 = str3.get(count3++);
        if (count4 >= str4.size()) {
            count4 = 0;
            System.out.println("数据归零");
        }
        String s4 = str4.get(count4++);
        if (count5 >= str5.size()) {
            count5 = 0;
            System.out.println("数据归零");
        }
        String s5 = str5.get(count5++);
        if (count6 >= str6.size()) {
            count6 = 0;
            System.out.println("数据归零");
        }
        String s6 = str6.get(count6++);

        if (count7 >= str7.size()) {
            count7 = 0;
            System.out.println("数据归零");
        }
        String s7 = str7.get(count7++);
        if (count8 >= str8.size()) {
            count8 = 0;
            System.out.println("数据归零");
        }
        String s8 = str8.get(count8++);

        if (count9 >= str9.size()) {
            count9 = 0;
            System.out.println("数据归零");
        }
        String s9 = str9.get(count9++);

        if (count10 >= str10.size()) {
            count10 = 0;
            System.out.println("数据归零");
        }
        String s10 = str10.get(count10++);

        if (count11 >= str11.size()) {
            count11 = 0;
            System.out.println("数据归零");
        }
        String s11 = str11.get(count11++);

        KafkaUtils.SendMessage("task_Radar", s0);
        KafkaUtils.SendMessage("task_Radar", s1);
        KafkaUtils.SendMessage("task_Radar", s2);
        KafkaUtils.SendMessage("task_Radar", s3);
        KafkaUtils.SendMessage("task_Radar", s4);
        KafkaUtils.SendMessage("task_Radar", s5);
        KafkaUtils.SendMessage("task_Radar", s6);
        KafkaUtils.SendMessage("task_Radar", s7);
        KafkaUtils.SendMessage("task_Radar", s8);
        KafkaUtils.SendMessage("task_Radar", s9);
        KafkaUtils.SendMessage("task_Radar", s10);
        KafkaUtils.SendMessage("task_Radar", s11);
        System.out.println("----------------------航迹数据更新----------------------");
    }
}
