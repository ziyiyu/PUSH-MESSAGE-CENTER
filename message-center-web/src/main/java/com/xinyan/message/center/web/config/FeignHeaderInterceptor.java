package com.xinyan.message.center.web.config;

import com.xinyan.message.center.common.utils.LogUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Module Desc:
 *
 * @author: gambo
 * DateTime: 2018/9/17
 */
@Slf4j
@Component
public class FeignHeaderInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String logId = LogUtil.getLogId();
        requestTemplate.header("logId",logId);
    }

}
