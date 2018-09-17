/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.dal.mapper;

import com.xinyan.message.center.dal.model.DeviceInstaceDO;

import java.util.List;
import java.util.Map;

/**
 * @author luojitao
 * @version Id: DeviceInstanceDOMapper.java, v 0.1 2018/9/11 16:58 luojitao Exp $$
 */
public interface DeviceInstanceDOMapper {

    /**
     *获取所有设备实例信息
     * @return
     */
    List<String> selectGroupInstanceByXYID(List<String> xyIds);

    /**
     * 条件查询获取XYID实例信息
     * @param condition
     * @return
     */
    DeviceInstaceDO selectInstanceByCondition(Map condition);


}
