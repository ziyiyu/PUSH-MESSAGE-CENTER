package com.xinyan.message.center.web.controller;

import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ServiceException;
import com.xinyan.message.center.facade.model.request.PushInstanceReqDTO;
import com.xinyan.message.center.facade.model.response.PushInstanceResDTO;
import com.xinyan.message.center.facade.validate.JsrGroup;
import com.xinyan.message.center.service.ServerInstanceService;
import com.xinyan.message.center.web.model.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <ul>
 * <li>消息推送服务器实例信息控制层</li>
 * <li>author:beal; date:2018/9/20</li>
 * </ul>
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "server")
@Api(description = "服务器实例")
public class ServerInstanceController {

    /**
     * 服务器实例信息业务逻辑层
     */
    @Autowired
    private ServerInstanceService serverInstanceService;

    /**
     * 新增服务器实例信息
     *
     * @param   record                      新增服务器实例信息
     * @return  Result<String>              返回结果集
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
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
     * 查询服务器实例信息(oms)
     *
     * @param   record                           查询服务器实例信息请求
     * @return  Result<PushInstanceResDTO>       返回结果集
     */
    @RequestMapping(value = "/query",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<PushInstanceResDTO> queryInstance(@Validated({JsrGroup.Query.class})@RequestBody PushInstanceReqDTO record, BindingResult bindingResult){
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
    @RequestMapping(value = "/modify",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
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
    @RequestMapping(value = "/del",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
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
