package com.xinyan.message.center.facade;

import com.xinyan.message.center.facade.model.request.PushInstanceReqDTO;
import com.xinyan.message.center.facade.model.response.CenterApiResponse;
import com.xinyan.message.center.facade.model.response.PushInstanceResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <ul>
 * <li>服务器实例查询接口</li>
 * <li>author:beal; date:2018/9/17</li>
 * </ul>
 */
@FeignClient(value = "push-message-center")
public interface ServerInstanceFacede {

    /**
     * 服务器实例查询接口
     *
     * @param  record                服务器实例查询参数
     * @return PushInstanceResDTO    返回结果集
     */
    @RequestMapping(value = "/message/queryInstance",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    CenterApiResponse<PushInstanceResDTO> queryInstance(@RequestParam PushInstanceReqDTO record);

}
