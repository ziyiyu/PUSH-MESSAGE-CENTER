package com.xinyan.message.center.manager;

import com.xinyan.common.copy.BeanCopyUtil;
import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ManagerException;
import com.xinyan.message.center.dal.mapper.InstanceDOMapper;
import com.xinyan.message.center.dal.model.PushInstanceDO;
import com.xinyan.message.center.facade.model.request.PushInstanceReqDTO;
import com.xinyan.message.center.facade.model.response.PushInstanceResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <ul>
 * <li>服务器实例信息管理层</li>
 * <li>author:beal; date:2018/9/17</li>
 * </ul>
 */
@Slf4j
@Component
public class ServerInstanceManager {

    /**
     * 服务器实例信息数据库层
     */
    @Autowired
    private InstanceDOMapper instanceDOMapper;

    /**
     * 新增服务器实例信息
     *
     * @param  record                新增服务器实例信息参数
     * @return PushInstanceDO        返回结果集
     */
    public void addServerInstance(PushInstanceReqDTO record){
        try {
            PushInstanceDO reqrecord = BeanCopyUtil.objConvert(record,PushInstanceDO.class);
            int i = instanceDOMapper.addBySelective(reqrecord);
            if (i < 1) {
                throw new ManagerException(ErrorMsgEnum.DATA_ADD_FAIL);
            }
        } catch (Exception e) {
            throw new ManagerException(ErrorMsgEnum.DATA_ADD_FAIL, e);
        }
    }

    /**
     * 删除服务器实例信息
     *
     * @param  record                删除服务器实例信息参数
     * @return PushInstanceDO        返回结果集
     */
    public void delServerInstance(PushInstanceReqDTO record){
        try {
            PushInstanceDO reqrecord = BeanCopyUtil.objConvert(record,PushInstanceDO.class);
            int i = instanceDOMapper.deleteByPrimaryKey(reqrecord);
            if (i != 1) {
                throw new ManagerException(ErrorMsgEnum.DATA_DEL_FAIL);
            }
        } catch (Exception e) {
            throw new ManagerException(ErrorMsgEnum.DATA_DEL_FAIL, e);
        }
    }

    /**
     * 查询服务器实例信息
     *
     * @param  record                    查询服务器实例信息参数
     * @return PushInstanceResDTO        返回结果集
     */
    public PushInstanceResDTO queryServerInstance(PushInstanceReqDTO record){
        try {
            PushInstanceDO reqrecord = BeanCopyUtil.objConvert(record,PushInstanceDO.class);
            PushInstanceDO response = instanceDOMapper.selectBySelective(reqrecord);
            return BeanCopyUtil.objConvert(response,PushInstanceResDTO.class);
        } catch (Exception e) {
            throw new ManagerException(ErrorMsgEnum.DATA_QUERY_FAIL, e);
        }
    }

    /**
     * 更新服务器实例信息
     *
     * @param  record                更新服务器实例信息参数
     * @return                       返回结果集
     */
    public void modifyServerInstance(PushInstanceReqDTO record){
        try {
            PushInstanceDO reqrecord = BeanCopyUtil.objConvert(record,PushInstanceDO.class);
            int i = instanceDOMapper.updateByPrimaryKey(reqrecord);
            if (i != 1) {
                throw new ManagerException(ErrorMsgEnum.DATA_UPDATE_FAIL);
            }
        } catch (Exception e) {
            throw new ManagerException(ErrorMsgEnum.DATA_UPDATE_FAIL, e);
        }
    }

}
