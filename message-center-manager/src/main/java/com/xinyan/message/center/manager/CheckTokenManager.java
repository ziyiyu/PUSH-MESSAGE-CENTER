package com.xinyan.message.center.manager;

import com.alibaba.fastjson.JSON;
import com.xinyan.common.copy.BeanCopyUtil;
import com.xinyan.message.center.common.constant.CacheConsts;
import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ManagerException;
import com.xinyan.message.center.common.utils.AesUtil;
import com.xinyan.message.center.common.utils.CreateNewTokenUtils;
import com.xinyan.message.center.common.utils.ObjectUtils;
import com.xinyan.message.center.common.utils.RedisManagerHelp;
import com.xinyan.message.center.dal.mapper.InstanceDOMapper;
import com.xinyan.message.center.dal.mapper.XyIdPushDOMapper;
import com.xinyan.message.center.dal.model.PushInstanceDO;
import com.xinyan.message.center.facade.DeviceServiceFacade;
import com.xinyan.message.center.facade.model.Response;
import com.xinyan.message.center.facade.model.request.CheckTokenReqDTO;
import com.xinyan.message.center.facade.model.request.DeviceQueryReqDTO;
import com.xinyan.message.center.facade.model.response.CheckTokenResDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author:songhongyu
 * Date:2018/8/8 20:35
 * Version:1.0
 */
@Component
@Slf4j
public class CheckTokenManager {

    @Value("${key.memberId}")
    private String memberId;

    @Autowired
    private RedisManagerHelp redisManagerHelp;

    @Autowired
    private DeviceServiceFacade deviceServiceFacade;

    @Autowired
    private InstanceDOMapper instanceDOMapper;

    @Autowired
    private XyIdPushDOMapper xyIdPushDOMapper;

    /**
     * 验证token是否存在
     *
     * @param
     * @return java.lang.Boolean
     * @author gambo
     * @date 2018/8/17 12:26
     **/
    public Boolean checkTokenValuable(String token, CheckTokenResDTO resDTO) {
        try {
            //用户多次请求并且token未失效
            if (StringUtils.isNotBlank(token)){
                CheckTokenResDTO result = redisManagerHelp.getObj(CacheConsts.INSTANCE_TOKEN_PREFIX+token, CheckTokenResDTO.class);
                if (ObjectUtils.isNotNull(result)) {
                    PushInstanceDO pushInstanceDO = redisManagerHelp.getObj(CacheConsts.INSTANCE_SERVER_PREFIX+result.getInstanceId(), PushInstanceDO.class);
                    if (ObjectUtils.isNull(pushInstanceDO)) {
                        pushInstanceDO = instanceDOMapper.selectByInstanceId(result.getInstanceId());
                        redisManagerHelp.insertObj(CacheConsts.INSTANCE_SERVER_PREFIX+result.getInstanceId(), pushInstanceDO,60 * 60 * 24 * 3L);
                    }
                    //判断当前服务器是否可用
                    if (ObjectUtils.isNotNull(pushInstanceDO) && "up".equals(pushInstanceDO.getServerStatus())) {
                        resDTO.setToken(result.getToken());
                        resDTO.setInstanceId(result.getInstanceId());
                        resDTO.setIpPort(result.getIpPort());
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            throw new ManagerException(ErrorMsgEnum.TOKEN_APPROVE_ERROR);
        }
    }

    /**
     * 验证xyid是否有效
     *
     * @param
     * @return java.lang.Boolean
     * @author gambo
     * @date 2018/8/17 12:26
     **/
    public Boolean checkSignValuable(CheckTokenReqDTO reqDTO) {
        try {
            String xyId = AesUtil.decryptSign(reqDTO.getDfpSign(), memberId);
            //解密失败，查询设备指纹
            if (StringUtils.isBlank(xyId)) {
                DeviceQueryReqDTO queryReqDTO = new DeviceQueryReqDTO();
                queryReqDTO.setToken(reqDTO.getDfpToken());
                queryReqDTO.setMerchantNo(memberId);
                Response response = deviceServiceFacade.queryDeviceInfo(queryReqDTO);
                if (response != null && response.isSuccess() && response.getResult() != null) {
                    xyId = response.getResult().getXyid();
                    log.debug("查询设备指纹返回xyId:{};memberId:{}", xyId,memberId);
                }
            }
            reqDTO.setXyId(xyId);
            return StringUtils.isNotBlank(xyId);
        } catch (Exception e) {
            throw new ManagerException(ErrorMsgEnum.TOKEN_APPROVE_ERROR);
        }
    }

    /**
     * 查询缓存中xyId为key是否对应token和ipPort
     *
     * @param xyId
     * @param resDTO
     * @return java.lang.Boolean
     * @author gambo
     * @date 2018/8/17 13:18
     **/
    public Boolean queryRedisByXyId(String xyId, CheckTokenResDTO resDTO) {
        //验证token+ipPort是否存在
        try {
            CheckTokenResDTO result = redisManagerHelp.getObj(CacheConsts.INSTANCE_XYID_PREFIX+xyId, CheckTokenResDTO.class);
            if (ObjectUtils.isNotNull(result)) {
                PushInstanceDO pushInstanceDO = redisManagerHelp.getObj(CacheConsts.INSTANCE_SERVER_PREFIX+result.getInstanceId(), PushInstanceDO.class);
                if (ObjectUtils.isNull(pushInstanceDO)) {
                    pushInstanceDO = instanceDOMapper.selectByInstanceId(result.getInstanceId());
                    redisManagerHelp.insertObj(CacheConsts.INSTANCE_SERVER_PREFIX+result.getInstanceId(), pushInstanceDO,60 * 60 * 24 * 3L);
                }
                //判断当前服务器是否可用
                if ("up".equals(pushInstanceDO.getServerStatus())) {
                    resDTO.setToken(result.getToken());
                    resDTO.setInstanceId(result.getInstanceId());
                    resDTO.setIpPort(result.getIpPort());
                    return true;
                }
            }
        } catch (Exception e) {
            throw new ManagerException(ErrorMsgEnum.DATA_CHECK_FAIL,e);
        }
        return false;
    }

    /**
     * 生成新的token；存储xyId和token的关系；返回新的连接实例
     *
     * @param reqDTO
     * @return java.lang.Boolean
     * @author gambo
     * @date 2018/8/17 13:18
     **/
    public CheckTokenResDTO getNewInstanse(CheckTokenReqDTO reqDTO) {
        try {
            //生成新的token并添加到缓存中存储三天
            String md5Token = CreateNewTokenUtils.md5Token(reqDTO.getXyId());
            PushInstanceDO pushInstanceDO = instanceDOMapper.selectBestInstance();
            //将连接信息记录入库
            CheckTokenResDTO resDTO =  BeanCopyUtil.objConvert(pushInstanceDO, CheckTokenResDTO.class);
            resDTO.setToken(md5Token);
            resDTO.setXyId(reqDTO.getXyId());
            redisManagerHelp.insertObj(CacheConsts.INSTANCE_XYID_PREFIX+reqDTO.getXyId(), resDTO, 60 * 60 * 24 * 3L);
            redisManagerHelp.insertString(CacheConsts.INSTANCE_TOKEN_PREFIX+md5Token, JSON.toJSONString(resDTO), 60 * 60 * 24 * 3L);
            return resDTO;
        } catch (Exception e) {
            throw new ManagerException(ErrorMsgEnum.TOKEN_APPROVE_ERROR);
        }
    }

}
