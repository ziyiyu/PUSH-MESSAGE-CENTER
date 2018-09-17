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
 * @version Id: SubscribeReqDTO.java, v 0.1 2018/9/7 10:23 luojitao Exp $$
 */
@Data
@ApiModel("订阅接收信息")
public class SubscribeReqDTO implements Serializable{

    private static final long serialVersionUID = -1799553915545797444L;

    /** 设备列表 */
    @ApiModelProperty("设备列表")
    private String devices;

    /** 频道列表 */
    @ApiModelProperty("频道列表")
    private String topics;
}
