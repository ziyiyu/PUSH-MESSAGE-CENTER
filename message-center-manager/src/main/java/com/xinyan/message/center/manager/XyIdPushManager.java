package com.xinyan.message.center.manager;

import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ManagerException;
import com.xinyan.message.center.dal.mapper.XyIdPushDOMapper;
import com.xinyan.message.center.dal.model.XyIdPushDO;
import com.xinyan.message.center.facade.model.request.CheckTokenReqDTO;
import com.xinyan.message.center.facade.model.response.CheckTokenResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author:gambo
 * Date:2018/9/10 20:35
 * Version:1.0
 */
@Component
@Slf4j
public class XyIdPushManager {

    @Autowired
    XyIdPushDOMapper xyIdPushDOMapper;

    /**
     * 插入设备连接记录
     * @param reqDTO 请求连接参数
     * @param resDTO 连接返回响应参数
     **/
    public void addXyIdPush(CheckTokenReqDTO reqDTO, CheckTokenResDTO resDTO) {
        try {
            XyIdPushDO xyIdPushDO= new XyIdPushDO();
            xyIdPushDO.setXyId(reqDTO.getXyId());
            xyIdPushDO.setIpPort(resDTO.getIpPort());
            xyIdPushDO.setInstanceId(resDTO.getInstanceId());
            xyIdPushDOMapper.insertSelective(xyIdPushDO);
        } catch (Exception e) {
            throw new ManagerException(ErrorMsgEnum.INSTANCE_ADD_ERROR,e);
        }
    }

}
