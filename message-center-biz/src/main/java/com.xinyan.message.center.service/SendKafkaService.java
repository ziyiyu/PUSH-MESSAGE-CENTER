/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.service;

import com.xinyan.message.center.manager.kafka.SendKafkaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author luojitao
 * @version Id: SendKafkaService.java, v 0.1 2018/9/7 16:09 luojitao Exp $$
 */
@Slf4j
@Service
@Component
public class SendKafkaService {

    @Autowired
    private SendKafkaManager sendKafkaManager;

    public void sendKafkaMessage(String topic,String payload){

        sendKafkaManager.sendKafkaMessage(payload,topic);

    }
}
