package com.xinyan.message.center.service;

import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ManagerException;
import com.xinyan.message.center.common.exception.ServiceException;
import com.xinyan.message.center.facade.model.request.PushInstanceReqDTO;
import com.xinyan.message.center.facade.model.response.PushInstanceResDTO;
import com.xinyan.message.center.manager.ServerInstanceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <ul>
 * <li>服务器实例信息业务逻辑层</li>
 * <li>author:beal; date:2018/9/17</li>
 * </ul>
 */
@Slf4j
@Service
public class ServerInstanceService {

   /**
     * 服务器实例信息管理层
     */
    @Autowired
    private ServerInstanceManager serverInstanceManager;

    /**
     * 新增服务器实例信息
     *
     * @param record          新增服务器实例信息
     */
    public void addInstance(PushInstanceReqDTO record){
        try{
            serverInstanceManager.addServerInstance(record);
        }catch (ManagerException me){
            throw new ServiceException(me.getErrMsg(),me);
        }catch (Exception e){
            log.info("新增服务器实例异常信息：{}",e);
            throw new ServiceException(ErrorMsgEnum.DATA_ADD_FAIL);
        }
    }

    /**
     * 删除服务器实例信息
     *
     * @param record        删除服务器实例信息id
     */
    public void delInstance(PushInstanceReqDTO record){
        try{
            serverInstanceManager.delServerInstance(record);
        }catch (ManagerException me){
            throw new ServiceException(me.getErrMsg());
        }catch (Exception e){
            log.info("删除服务器实例异常信息：{}",e);
            throw new ServiceException(ErrorMsgEnum.DATA_DEL_FAIL);
        }
    }

    /**
     * 查询服务器实例信息
     *
     * @param record    查询服务器实例信息
     */
    public List<PushInstanceResDTO> queryInstance(PushInstanceReqDTO record){
        try{
            return serverInstanceManager.queryServerInstance(record);
        }catch (ManagerException me){
            throw new ServiceException(me.getErrMsg());
        }catch (Exception e){
            log.info("查询服务器实例异常信息：{}",e);
            throw new ServiceException(ErrorMsgEnum.DATA_QUERY_FAIL);
        }
    }

    /**
     * 更新服务器实例信息
     *
     * @param record    更新服务器实例信息
     */
    public void modifyInstance(PushInstanceReqDTO record){
        try{
            serverInstanceManager.modifyServerInstance(record);
        }catch (ManagerException me){
            throw new ServiceException(me.getErrMsg());
        }catch (Exception e){
            log.info("更新服务器实例异常信息：{}",e);
            throw new ServiceException(ErrorMsgEnum.DATA_UPDATE_FAIL);
        }
    }


    /**
     * 根据instanceId更新服务器实例信息
     *
     * @param record    更新服务器实例信息
     */
    public void modifyServerInstanceOfInstanceId(PushInstanceReqDTO record){
        try{
            serverInstanceManager.modifyServerInstanceOfInstanceId(record);
        }catch (ManagerException me){
            throw new ServiceException(me.getErrMsg());
        }catch (Exception e){
            log.info("更新服务器实例异常信息：{}",e);
            throw new ServiceException(ErrorMsgEnum.DATA_UPDATE_FAIL);
        }
    }
}
