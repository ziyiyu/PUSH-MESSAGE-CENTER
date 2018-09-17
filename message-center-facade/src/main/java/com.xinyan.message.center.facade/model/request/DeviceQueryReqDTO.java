package com.xinyan.message.center.facade.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Author:songhongyu
 * Date:2018/8/15 14:29
 * Version:1.0
 */
@Data
public class DeviceQueryReqDTO implements Serializable{

    /**
     * 设备指纹唯一标识（必填）
     */
    private String token;

    /**
     * 新颜标识客户身份的编号（必填）
     */
    private String merchantNo;

    /**
     * 商户业务信息,可用于业务埋点（非必填）
     */
    private String bizInfo;



}
