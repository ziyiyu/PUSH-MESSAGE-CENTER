package com.xinyan.message.center.facade.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Module Desc:缓存token判断信息返回参数
 * User: gambo
 * DateTime: 2018/8/8
 */
@Data
public class CheckTokenResDTO implements Serializable{

    /**
     * 串行版本ID
     */
    private static final long serialVersionUID = -5526116344026940880L;

    /** 缓存token */
    private String token;

    /** ip端口 */
    private String ipPort;

    /** 设备指纹ID */
    private String xyId;

    /** 实例ID */
    private String instanceId;

}
