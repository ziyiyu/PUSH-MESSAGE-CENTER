/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.dal.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luojitao
 * @version Id: DeviceInstaceDO.java, v 0.1 2018/9/11 16:59 luojitao Exp $$
 */

@Data
@Api("实例设备映射表")
public class DeviceInstaceDO extends BaseDO implements Serializable{

    /** 串行版本号 */
    private static final long serialVersionUID = -7729089735593848532L;

    /** 实例ID */
    @ApiModelProperty("实例ID")
    private String instanceId;

    /** token */
    @ApiModelProperty("token")
    private String token;

    /** IP端口 */
    @ApiModelProperty("IP端口")
    private String ipPort;


}
