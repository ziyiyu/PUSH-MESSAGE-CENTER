/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.service.async;

import com.xinyan.message.center.manager.kafka.SendKafkaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

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
    public Future<Boolean> asyncSendKafkaMessage(String body, String topic){
        try{
            boolean flag=sendKafkaManager.sendKafkaMessage(body,topic);
            return new AsyncResult<>(flag);
        }catch (Exception e){
            log.error("AsyncTasks.asyncSendKafkaMessage.exception",e);
            return new AsyncResult<>(false);
        }

    }
}
