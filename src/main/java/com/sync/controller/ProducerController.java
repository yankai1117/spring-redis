package com.sync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(value = "/kafka")
@Controller
public class ProducerController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/producer",method = RequestMethod.GET)
    public void consume(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String value = "怎么会这样？";
        for (int i = 1; i<=500; i++){
            kafkaTemplate.send("result",value);
        }
    }
}