package com.xinyan.message.center.facade;

import com.xinyan.message.center.facade.model.request.DeviceQueryReqDTO;
import com.xinyan.message.center.facade.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description:
 * Author:songhongyu
 * Date:2018/8/15 14:22
 * Version:1.0
 */
@FeignClient("device-engine-query")
public interface DeviceServiceFacade {

    @RequestMapping(value = "/query/v1/queryByToken", method = RequestMethod.POST)
    Response queryDeviceInfo(@RequestBody DeviceQueryReqDTO reqDTO);

}
