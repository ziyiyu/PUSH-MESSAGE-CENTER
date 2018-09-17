/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.service.async;

import com.xinyan.message.center.manager.kafka.SendKafkaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author luojitao
 * @version Id: AsyncTasks.java, v 0.1 2018/9/11 10:52 luojitao Exp $$
 */
@Component
@Slf4j
public class AsyncTasks {

    @Autowired
    private SendKafkaManager sendKafkaManager;


    @Async
    public void asyncSendKafkaMessage(String body,String topic){
        sendKafkaManager.sendKafkaMessage(body,topic);
    }
}
