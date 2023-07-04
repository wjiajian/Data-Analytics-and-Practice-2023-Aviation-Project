package com.qrsoft.util;

import kafka.serializer.StringEncoder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/***
 * kafka工具类
 */
public class KafkaUtils {
    @SuppressWarnings("rawtypes")
    private static KafkaProducer _producer;

    /**
     * 获取kafka生产者消息
     */
    @SuppressWarnings("rawtypes")
    private static KafkaProducer GetProducer() {
        if (_producer == null) {
            _producer = createProducer();
        }
        return _producer;
    }

    /**
     * 向kafka中传入数据
     *
     * @param topic   topic名称
     * @param message 消息
     * @return true成功，false失败
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static boolean SendMessage(String topic, String message) {
        // 创建一个producer的对象
        Producer producer = GetProducer();
        try {
            // 使用produer发送消息
            producer.send(new ProducerRecord(topic, "message" + message, message));
            //TimeUnit.SECONDS.sleep(5);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * 创建Producer的实例

     */
    @SuppressWarnings("rawtypes")
    private static KafkaProducer createProducer()  {

        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("kafka.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String bootstrap = properties.get("bootstrap.servers").toString();
        String zookeeper = properties.get("zookeeper.connect").toString();
        String metadata = properties.get("metadata.broker.list").toString();


        Properties props = new Properties();
        // 该地址是集群的子集，用来探测集群。
        props.put("bootstrap.servers", bootstrap);

        // 声明zk
        props.put("zookeeper.connect", zookeeper);
        props.put("serializer.class", StringEncoder.class.getName());

        // 声明Broker的地址
        props.put("metadata.broker.list", metadata);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return new KafkaProducer(props);
    }
}