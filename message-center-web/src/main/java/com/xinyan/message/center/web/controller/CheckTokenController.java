package com.xinyan.message.center.web.controller;

import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ServiceException;
import com.xinyan.message.center.facade.model.request.CheckTokenReqDTO;
import com.xinyan.message.center.facade.model.response.CheckTokenResDTO;
import com.xinyan.message.center.service.CheckTokenService;
import com.xinyan.message.center.web.model.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 本地缓存token判断并查询IP端口控制层
 * Author:songhongyu
 * Date:2018/8/8 14:46
 * Version:1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/checkToken")
@Api(description = "token判断并查询ip端口")
public class CheckTokenController {

    @Autowired
    private CheckTokenService checkTokenService;

    @RequestMapping(value = "/getInstance", method = RequestMethod.POST)
    public Result<CheckTokenResDTO> checkAndGetInstance(@RequestBody CheckTokenReqDTO record) {
        try {
            log.info("token认证 请求:{}", record);
            CheckTokenResDTO checkTokenResDTO = checkTokenService.checkAndQuery(record);
            log.info("token认证 结束 结果:{}",checkTokenResDTO);
            return new Result<>(checkTokenResDTO);
        } catch (ServiceException se) {
            return new Result<>(se.getErrorMsgEnum());
        } catch (Exception e) {
            log.error("请求参数:{}", record, e);
            return new Result<>(ErrorMsgEnum.SYSTEM_ERROR);
        }

    }
}
