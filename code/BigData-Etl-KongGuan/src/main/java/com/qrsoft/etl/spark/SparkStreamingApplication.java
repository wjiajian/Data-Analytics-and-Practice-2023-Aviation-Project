package com.qrsoft.etl.spark;

import com.alibaba.fastjson.JSONObject;
import com.qrsoft.etl.common.Constants;
import com.qrsoft.etl.util.ConfigUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class SparkStreamingApplication implements Serializable {
    static final Pattern SPACE = Pattern.compile(" ");
    // 多个以逗号隔开
    static String brokers = ConfigUtil.readProperty("brokers");
    static String zkserver = ConfigUtil.readProperty("zkserver");

    // 消费者组名称
    static String groupId = "spark_etl";
    // topic列表
    static String topicsStr = Constants.TASK_RADAR;
    static String[] topicsStrs = {
            Constants.TASK_PlANDATA,
            Constants.TASK_WARNFLIGHT,
            Constants.TASK_ATCDUTY,
            Constants.TASK_WARNSIMILAR,
            Constants.TASK_AFTN,
            Constants.TASK_ATC,
            Constants.TASK_CALLSATURATION,
            Constants.TASK_RADAR
    };
    /**
     * 启动Spark读取、清洗数据
     */
    public void SparkEtlStart() {
        // ... 在此处添加代码 ...
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("qst-etl");
//反压机制
        conf.set("spark.streaming.backpressure.enabled", "true");
        conf.set("allowMultipleContexts", "true");
        JavaSparkContext sc = new JavaSparkContext(conf);
// 获取jssc 以及设置获取流的时间
        JavaStreamingContext jssc = new JavaStreamingContext(sc, Durations.seconds(10));
        jssc.sparkContext().setLogLevel("WARN");
// Kafka 参数配置
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("zookeeper.connect", zkserver);
        kafkaParams.put("bootstrap.servers", brokers);
        kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);     //指定了KafkaConsumershuyu 哪一个消费者群组
        kafkaParams.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);

        kafkaParams.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        kafkaParams.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");   //读取Kafka最新的一条
        kafkaParams.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);

        Collection<String> topics = Arrays.asList(topicsStrs);

        // 获取流
        JavaInputDStream<ConsumerRecord<String, String>> stream = KafkaUtils.createDirectStream(jssc,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.Subscribe(topics, kafkaParams));

        stream.foreachRDD(rdd -> {
            rdd.foreach(t -> {
                String topName = t.topic();
                JSONObject jsonObject = new JSONObject();
                String taskRadar = "";
                if(topName.equals(Constants.TASK_RADAR)){
                    taskRadar = t.value();
                }else{
                    jsonObject = JSONObject.parseObject(t.value());
                }
                SparkUtil sparkUtil = new SparkUtil();
                try {
                    switch (topName) {
                        case Constants.TASK_RADAR:
//                            sparkUtil.TaskRadar(taskRadar);
                            sparkUtil.TaskRadarStr(taskRadar);
                            break;
                        case Constants.TASK_PlANDATA:
                            sparkUtil.TaskPlanData(jsonObject);
                            break;
                        case Constants.TASK_WARNFLIGHT:
                            sparkUtil.TaskWarnfLight(jsonObject);
                            break;
                        case Constants.TASK_ATCDUTY:
                            sparkUtil.TaskAtcduty(jsonObject);
                            break;
                        case Constants.TASK_WARNSIMILAR:
                            sparkUtil.TaskWarnsimilar(jsonObject);
                            break;
                        case Constants.TASK_AFTN:
                            sparkUtil.TaskAftn(jsonObject);
                            break;
                        case Constants.TASK_ATC:
                            sparkUtil.TaskAtc(jsonObject);
                            break;
                        case Constants.TASK_CALLSATURATION:
                            sparkUtil.TaskCallsaturation(jsonObject);
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                //return Arrays.asList(SPACE.split(t.value())).iterator();
            });
        });
        // 打印结果
//warns.print();
        try {
            // 启动
            jssc.start();
            jssc.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
