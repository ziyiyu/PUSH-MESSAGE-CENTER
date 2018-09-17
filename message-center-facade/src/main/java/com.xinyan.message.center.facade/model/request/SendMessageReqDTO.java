/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.facade.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luojitao
 * @version Id: SendMessageReqDTO.java, v 0.1 2018/9/7 10:08 luojitao Exp $$
 */
@Data
@ApiModel("消息推送接收信息")
public class SendMessageReqDTO implements Serializable{
    private static final long serialVersionUID = 1972280315760917676L;

    /** 是否第一次发送 */
    @ApiModelProperty("是否第一次发送")
    private Integer duplicate;

    /** 消息推送服务级别*/
    @ApiModelProperty("消息推送服务级别")
    private Integer qos;

    /** 消息ID*/
    @ApiModelProperty("消息ID")
    private String msgId;

    /** 推送的频道列表*/
    @ApiModelProperty("推送的频道列表")
    private Object topics;

    /** msgBody*/
    @ApiModelProperty("msgBody")
    private String payload;

    /** 消息来源*/
    @ApiModelProperty("消息来源")
    private String source;
}
