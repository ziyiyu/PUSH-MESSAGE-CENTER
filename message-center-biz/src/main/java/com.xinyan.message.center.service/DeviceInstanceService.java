/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.service;

import com.xinyan.message.center.common.constant.Constant;
import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.enums.OperateEnum;
import com.xinyan.message.center.common.exception.ServiceException;
import com.xinyan.message.center.common.utils.CommonUtils;
import com.xinyan.message.center.manager.DeviceInstanceManager;
import com.xinyan.message.center.service.async.AsyncTasks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luojitao
 * @version Id: DeviceInstanceService.java, v 0.1 2018/9/11 20:01 luojitao Exp $$
 */
@Slf4j
@Service
public class DeviceInstanceService {

    @Autowired
    private AsyncTasks asyncTasks;

    @Autowired
    private DeviceInstanceManager deviceInstanceManager;

    /**
     * 处理推送消息
     * @param map
     * @return
     */
    public String pubMsg(Map map){
        try {
            boolean flag=false;
            boolean ruleflag=false;
            List<String> topics= (List) map.get(Constant.TOPICS)!=null?(List)map.get(Constant.TOPICS):new ArrayList<>();
            for(String topic:topics){
                if(!CommonUtils.matchs(topic,Constant.DEVICE_PATTERN)){
                    //非设备 判断是否为频道
                    if(CommonUtils.matchs(topic,Constant.PATTERN)){
                        //为频道 全局广播
                        flag=true;
                        break;
                    }else {
                        //频道格式错误
                        ruleflag=true;
                        break;
                    }
                }
            }
            if(ruleflag){
                //频道格式错误
                return Constant.RESULT_TOPIC_ERROR;
            }

            //暂不开启精准推送，直接进行全局广播
            asyncTasks.asyncSendKafkaMessage(CommonUtils.pareseToString(map),Constant.KAFKA_TOPIC_BASIC+Constant.GLOBAL);
/*
            if(flag){
                //存在频道 进行全局广播
                asyncTasks.asyncSendKafkaMessage(CommonUtils.pareseToString(map),Constant.KAFKA_TOPIC_BASIC+Constant.GLOBAL);
                return Constant.RESULT_SUCCESS;
            }

            if(topics.size()>Constant.CHANNEL_MAX.get()){
                //频道数量超出阈值，直接进行全局广播
                asyncTasks.asyncSendKafkaMessage(CommonUtils.pareseToString(map),Constant.KAFKA_TOPIC_BASIC+Constant.GLOBAL);
                return Constant.RESULT_SUCCESS;
            }
            List<String> result=deviceInstanceManager.selectGroupInstanceByXYID(topics);
            for (String instanceId:result){
                asyncTasks.asyncSendKafkaMessage(CommonUtils.pareseToString(map),Constant.KAFKA_TOPIC_BASIC+instanceId);
            }*/
            return Constant.RESULT_SUCCESS;
        }catch (Exception e){
            throw new ServiceException(ErrorMsgEnum.SEND_MESSAGE_ERROR);
        }
    }

    /**
     * 校验设备及频道格式
     * @param map
     * @return
     */
    public String checkDevicesTopics(Map map){
        boolean flag=false;
        List<String> devices=(List) map.get(Constant.DEVICES)!=null?(List)map.get(Constant.DEVICES):new ArrayList<>();
        List<String> topics=(List) map.get(Constant.TOPICS)!=null?(List)map.get(Constant.TOPICS):new ArrayList<>();

        for (String device:devices){
            if(!CommonUtils.matchs(device,Constant.DEVICE_PATTERN)){
                //设备格式不合法
                flag=true;
                break;
            }
        }
        if(flag){
            return Constant.RESULT_DEVICE_ERROR;
        }

        for (String topic:topics){
            if(!CommonUtils.matchs(topic,Constant.PATTERN)){
                //频道格式不合法
                flag=true;
                break;
            }
        }
        if(flag){
            return  Constant.RESULT_TOPIC_ERROR;
        }
        return Constant.RESULT_SUCCESS;
    }
}
