package com.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @ClassName KafkaSendService
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/310:46
 * @Version 1.0.0
 */
//@EnableBinding(Source.class)
public class KafkaSendService {
//    @Autowired
//    private Source source;
//    public void sendMessage(String msg) {
//        try{
//            source.output().send(MessageBuilder.withPayload(msg).build());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    //接受的是一个实体类，具体配置在application.yml
//    public void sendMessage(TransMsg msg) {
//        try {
//            //MessageBuilder.withPayload(msg).setHeader(KafkaHeaders.TOPIC,"111111").build();
//            source.output().send(MessageBuilder.withPayload(msg).build());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
