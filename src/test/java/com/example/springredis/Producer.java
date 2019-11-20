package com.example.springredis;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;

/**
 * @ClassName Producer
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/410:57
 * @Version 1.0.0
 */
public class Producer {

    public static void main(String[] args){

        int events = 100;
        Properties props = new Properties();
        //集群地址，多个服务器用"，"分隔
        props.put("bootstrap.servers", "139.186.66.219:9092");
        //key、value的序列化，此处以字符串为例，使用kafka已有的序列化类
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.put("partitioner.class", "com.kafka.demo.Partitioner");//分区操作，此处未写
        props.put("request.required.acks", "1");
        //创建生产者
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < events; i++){
            long runtime = new Date().getTime();
            String ip = "192.168.1." + i;
            String msg = runtime + "时间的模拟ip：" + ip;
            //写入名为"test-partition-1"的topic
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test-partition-1", "key-"+i, msg);
            producer.send(producerRecord);
            System.out.println("写入test-partition-1：" + msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }
}