/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.manager.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

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

    public boolean sendKafkaMessage( String body, String topic) {
        return sendMessage(body, topic);
    }




    private boolean sendMessage(String body, String topic) {
        return sendMessage(body.getBytes(), topic);
    }

    private boolean sendMessage(byte[] body, String topic) {
        return resolver.resolveDestination(topic).send(new GenericMessage<>(body));
    }


}
