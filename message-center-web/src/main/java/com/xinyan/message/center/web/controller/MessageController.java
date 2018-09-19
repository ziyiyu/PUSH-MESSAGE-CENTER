/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.web.controller;

import com.xinyan.message.center.common.constant.Constant;
import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.enums.OperateEnum;
import com.xinyan.message.center.common.exception.ServiceException;
import com.xinyan.message.center.common.utils.CommonUtils;
import com.xinyan.message.center.common.utils.LogUtil;
import com.xinyan.message.center.facade.model.request.PushInstanceReqDTO;
import com.xinyan.message.center.facade.model.response.MessageResDTO;
import com.xinyan.message.center.facade.model.response.PushInstanceResDTO;
import com.xinyan.message.center.facade.validate.JsrGroup;
import com.xinyan.message.center.service.DeviceInstanceService;
import com.xinyan.message.center.service.ServerInstanceService;
import com.xinyan.message.center.service.async.AsyncTasks;
import com.xinyan.message.center.web.model.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Description 推送消息
 * @author luojitao
 * @version Id: PushMessageController.java, v 0.1 2018/9/6 17:03 luojitao Exp $$
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/message")
@Api(description = "推送消息")
public class MessageController {

    /**
     * 生产消息任务层
     */
    @Autowired
    private AsyncTasks asyncTasks;

    /**
     * 设备实例信息业务逻辑层
     */
    @Autowired
    private DeviceInstanceService deviceInstanceService;

    /**
     * 服务器实例信息业务逻辑层
     */
    @Autowired
    private ServerInstanceService serverInstanceService;

    /**
     * 推送消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/pubMsg",method = RequestMethod.POST)
    public Result<MessageResDTO> pubMsgInstance(@RequestBody String message){
        MessageResDTO sendMessageResDTO=new MessageResDTO();
        try {
            Map<String,Object> map= CommonUtils.parseToMap(message);
            map.put(Constant.OPERATETYPE, OperateEnum.PUB_MSG.getCode());
            //设置LOGID与平台端一致
            LogUtil.updateLogId(map.get(Constant.LOG_UUID)!=null?map.get(Constant.LOG_UUID).toString(): LogUtil.getLogId());
            sendMessageResDTO.setRequestId(map.get(Constant.MSGID)!=null?map.get(Constant.MSGID).toString(): CommonUtils.getmsgId());
            //将平台信息传入service进行逻辑处理
            String result=deviceInstanceService.pubMsg(map);
            if(result.equals(Constant.RESULT_TOPIC_ERROR)){
                //频道格式错误 直接返回错误代码
                return new Result<>(ErrorMsgEnum.TOPIC_TYPE_ERROR);
            }else if(result.equals(Constant.RESULT_KAFKA_ERROR)){
                return new Result<>(ErrorMsgEnum.KAFKA_SEND_ERROR);
            }
            return new Result<>(sendMessageResDTO);
        }catch (ServiceException se){
            log.error("MessageController.pubMsgInstance.ServiceException",se);
        return new Result<>(ErrorMsgEnum.SEND_MESSAGE_ERROR);
        }catch (Exception e){
            log.error("MessageController.pubMsgInstance.exception.",e);
        return new Result<>(ErrorMsgEnum.SYSTEM_ERROR);
        }
    }


    /**
     * 订阅消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/subMsg",method = RequestMethod.POST)
    public Result<MessageResDTO> subscribeMsgInstance(@RequestBody String message){
        MessageResDTO sendMessageResDTO=new MessageResDTO();
        try {
            Map<String,String> map= CommonUtils.parseToMap(message);
            map.put(Constant.OPERATETYPE, OperateEnum.SUB_MSG.getCode());
            //设置LOGID与平台端一致
            LogUtil.updateLogId(map.get(Constant.LOG_UUID)!=null?map.get(Constant.LOG_UUID): LogUtil.getLogId());
            sendMessageResDTO.setRequestId(map.get(Constant.requestId)!=null?map.get(Constant.requestId).toString(): CommonUtils.getmsgId());
            //校验频道及设备格式
            String result=deviceInstanceService.checkDevicesTopics(map);
            if(Constant.RESULT_TOPIC_ERROR.equals(result)){
                //频道格式错误
                return new Result<>(ErrorMsgEnum.TOPIC_TYPE_ERROR);
            }
            if(Constant.RESULT_DEVICE_ERROR.equals(result)){
                //设备格式错误
                return new Result<>(ErrorMsgEnum.DEVICE_TYPE_ERROR);
            }
            //全局广播订阅信息
            Future<Boolean> flag= asyncTasks.asyncSendKafkaMessage(CommonUtils.pareseToString(map), Constant.KAFKA_TOPIC_BASIC+ Constant.GLOBAL);
            if(!flag.get()){
                return new Result<>(ErrorMsgEnum.KAFKA_SEND_ERROR);
            }
            return new Result<>(sendMessageResDTO);
        }catch (ServiceException se){
            log.error("MessageController.subscribeMsgInstance.ServiceException",se);
            return new Result<>(ErrorMsgEnum.SEND_MESSAGE_ERROR);
        }catch (Exception e){
            log.error("MessageController.subscribeMsgInstance.exception.",e);
            return new Result<>(ErrorMsgEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 取消订阅
     * @param message
     * @return
     */
    @RequestMapping(value = "/unSubMsg",method = RequestMethod.POST)
    public Result<MessageResDTO> unSubscribeMsgInstance(@RequestBody String message){
        MessageResDTO sendMessageResDTO=new MessageResDTO();
        try {
            Map<String,String> map= CommonUtils.parseToMap(message);
            map.put(Constant.OPERATETYPE, OperateEnum.UNSUB_MSG.getCode());
            //设置LOGID与平台端一致
            LogUtil.updateLogId(map.get(Constant.LOG_UUID)!=null?map.get(Constant.LOG_UUID): LogUtil.getLogId());
            sendMessageResDTO.setRequestId(map.get(Constant.requestId)!=null?map.get(Constant.requestId).toString(): CommonUtils.getmsgId());
            //校验频道及设备格式
            String result=deviceInstanceService.checkDevicesTopics(map);
            if(Constant.RESULT_TOPIC_ERROR.equals(result)){
                //频道格式错误
                return new Result<>(ErrorMsgEnum.TOPIC_TYPE_ERROR);
            }
            if(Constant.RESULT_DEVICE_ERROR.equals(result)){
                //设备格式错误
                return new Result<>(ErrorMsgEnum.DEVICE_TYPE_ERROR);
            }
            //全局广播订阅信息
            Future<Boolean> flag=asyncTasks.asyncSendKafkaMessage(CommonUtils.pareseToString(map), Constant.KAFKA_TOPIC_BASIC+ Constant.GLOBAL);
            if(!flag.get()){
                return new Result<>(ErrorMsgEnum.KAFKA_SEND_ERROR);
            }
            return new Result<>(sendMessageResDTO);
        }catch (ServiceException se){
            log.error("MessageController.unSubscribeMsgInstance.ServiceException",se);
            return new Result<>(ErrorMsgEnum.SEND_MESSAGE_ERROR);
        }catch (Exception e){
            log.error("MessageController.unSubscribeMsgInstance.exception",e);
            return new Result<>(ErrorMsgEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 新增服务器实例信息
     *
     * @param   record                      新增服务器实例信息
     * @return  Result<String>              返回结果集
     */
    @RequestMapping(value = "/addInstance",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> addInstance(@Validated({JsrGroup.Add.class}) @RequestBody PushInstanceReqDTO record, BindingResult bindingResult){
        try {
            log.info("新增服务器实例信息请求参数,PushInstanceDO:{}",record);
            serverInstanceService.addInstance(record);
            log.info("新增服务器实例信息操作成功！");
            return new Result<>("ok");
        }catch (ServiceException se){
            return new Result<>(se.getErrMsg());
        }catch (Exception e){
            log.error("新增服务器实例信息异常:{}",e);
            return new Result<>(ErrorMsgEnum.DATA_ADD_FAIL);
        }
    }

    /**
     * 查询服务器实例信息
     *
     * @param   record                           查询服务器实例信息请求
     * @return  Result<PushInstanceResDTO>       返回结果集
     */
    @RequestMapping(value = "/queryInstance",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<PushInstanceResDTO> queryInstance(@Validated({JsrGroup.Query.class}) @RequestBody PushInstanceReqDTO record, BindingResult bindingResult){
        try {
            log.info("查询服务器实例信息请求参数,PushInstanceDO:{}",record);
            PushInstanceResDTO result = serverInstanceService.queryInstance(record);
            log.info("查询服务器实例信息返回结果:PushInstanceResDTO:{},操作成功！",result);
            return new Result<>(result);
        }catch (ServiceException se){
            return new Result<>(se.getErrorMsgEnum());
        }catch (Exception e){
            log.error("查询服务器实例信息异常:{}",e);
            return new Result<>(ErrorMsgEnum.DATA_QUERY_FAIL);
        }
    }

    /**
     * 更新服务器实例信息
     *
     * @param   record                      更新服务器实例信息请求
     * @return  Result<String>              返回结果集
     */
    @RequestMapping(value = "/modifyInstance",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> modifyInstance(@Validated({JsrGroup.Update.class}) @RequestBody PushInstanceReqDTO record, BindingResult bindingResult){
        try {
            log.info("更新服务器实例信息请求参数,PushInstanceDO:{}",record);
            serverInstanceService.modifyInstance(record);
            log.info("更新服务器实例信息操作成功！");
            return new Result<>("ok");
        }catch (ServiceException se){
            return new Result<>(se.getErrorMsgEnum());
        }catch (Exception e){
            log.error("更新服务器实例信息异常:{}",e);
            return new Result<>(ErrorMsgEnum.DATA_UPDATE_FAIL);
        }
    }

    /**
     * 删除服务器实例信息
     *
     * @param   record                      删除服务器实例信息id
     * @return  Result<String>              返回结果集
     */
    @RequestMapping(value = "/delInstance",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> delInstance(@Validated({JsrGroup.Delete.class}) @RequestBody PushInstanceReqDTO record, BindingResult bindingResult){
        try {
            log.info("删除服务器实例信息请求参数,PushInstanceDO:{}",record);
            serverInstanceService.delInstance(record);
            log.info("删除服务器实例信息操作成功！");
            return new Result<>("ok");
        }catch (ServiceException se){
            return new Result<>(se.getErrorMsgEnum());
        }catch (Exception e){
            log.error("删除服务器实例信息异常:{}",e);
            return new Result<>(ErrorMsgEnum.DATA_DEL_FAIL);
        }
    }

}
