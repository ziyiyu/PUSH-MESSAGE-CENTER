package com.xinyan.message.center.service;

import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ManagerException;
import com.xinyan.message.center.common.exception.ServiceException;
import com.xinyan.message.center.facade.model.request.CheckTokenReqDTO;
import com.xinyan.message.center.facade.model.response.CheckTokenResDTO;
import com.xinyan.message.center.manager.CheckTokenManager;
import com.xinyan.message.center.manager.XyIdPushManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.xinyan.message.center.dal.model.CheckTokenResDTO;

/**
 * Description: 本地token校验并查询ip端口 业务层
 * Author:songhongyu
 * Date:2018/8/9 11:11
 * Version:1.0
 */
@Slf4j
@Service
public class CheckTokenService {


    @Autowired
    private CheckTokenManager checkTokenManager;

    @Autowired
    private XyIdPushManager xyIdPushManager;

    /**
     * 判断token是否失效，如果失效返回新的token和连接实例，否则返回空
     * @param reqDTO 请求连接参数
     * @return com.xinyan.message.center.dal.model.CheckTokenResDTO
     **/
    public CheckTokenResDTO checkAndQuery(CheckTokenReqDTO reqDTO) {
        try {
            CheckTokenResDTO checkTokenResDTO = new CheckTokenResDTO();
            //验证token是否有效,有效直接返回
            if (checkTokenManager.checkTokenValuable(reqDTO.getToken(),checkTokenResDTO)) {
                return checkTokenResDTO;
            }

            //token不存在，验证xyId是否有效
            if (!checkTokenManager.checkSignValuable(reqDTO)) {
                log.info("sign无法解析成有效的xyId，sign:{}",reqDTO.getDfpSign());
                throw new ServiceException(ErrorMsgEnum.INVALID_SIGN);
            }

            //xyId有效，验证缓存中是否存在token和ipPort,存在直接返回
            if (checkTokenManager.queryRedisByXyId(reqDTO.getXyId(),checkTokenResDTO)){
                return checkTokenResDTO;
            }

            //token失效，xyid有效，生成新的token和连接实例
            checkTokenResDTO =  checkTokenManager.getNewInstanse(reqDTO);

            //将连接实例插入数据库
            xyIdPushManager.addXyIdPush(reqDTO,checkTokenResDTO);
            return checkTokenResDTO;

        } catch (ManagerException e) {
            log.error("token校验并查询ip端口 业务异常 参数:[{}] case", reqDTO, e);
            throw new ServiceException(e.getErrorMsgEnum());
        } catch (Exception e) {
            log.error("token校验并查询ip端口 业务异常 参数:[{}] case", reqDTO, e);
            throw new ServiceException(ErrorMsgEnum.FAILD);
        }

    }


}
