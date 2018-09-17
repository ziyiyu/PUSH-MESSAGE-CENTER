/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.manager;

import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ManagerException;
import com.xinyan.message.center.dal.mapper.DeviceInstanceDOMapper;
import com.xinyan.message.center.dal.model.DeviceInstaceDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author luojitao
 * @version Id: PushInstanceManager.java, v 0.1 2018/9/11 19:30 luojitao Exp $$
 */
@Component
@Slf4j
public class DeviceInstanceManager {


    @Autowired
    private DeviceInstanceDOMapper deviceInstanceDOMapper;


    /**
     * 条件查询获取单一设备实例信息
     * @param condition
     * @return
     */
    public DeviceInstaceDO selectInstanceByCondition(Map<String,String> condition){

        try {
            DeviceInstaceDO deviceInstaceDO=deviceInstanceDOMapper.selectInstanceByCondition(condition);

            return deviceInstaceDO;

        }catch (Exception e){
            throw new ManagerException(ErrorMsgEnum.DATA_QUERY_FAIL);
        }


    }

    /**
     * 根据xyid获取所属服务器实例ID
     * @param xyIds
     * @return
     */
    public List<String> selectGroupInstanceByXYID(List<String> xyIds){
        try {
            return deviceInstanceDOMapper.selectGroupInstanceByXYID(xyIds);
        }catch (Exception e){
            throw new ManagerException(ErrorMsgEnum.DATA_QUERY_FAIL);
        }
    }

}
