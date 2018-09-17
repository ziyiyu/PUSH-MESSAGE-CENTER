/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.manager.kafka;

import com.xinyan.message.center.common.constant.Constant;
import com.xinyan.message.center.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luojitao
 * @version Id: SendKafkaManager.java, v 0.1 2018/9/7 16:04 luojitao Exp $$
 */
@EnableBinding
@Component
@Slf4j
public class SendKafkaManager {

    @Autowired
    private BinderAwareChannelResolver resolver;

    public void sendKafkaMessage( String body, String topic) {
        sendMessage(body, topic);
    }




    private void sendMessage(String body, String topic) {
        sendMessage(body.getBytes(), topic);
    }

    private void sendMessage(byte[] body, String topic) {
        resolver.resolveDestination(topic).send(new GenericMessage<>(body));
    }


}
