package com.xinyan.message.center.facade.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Author:songhongyu
 * Date:2018/8/15 14:29
 * Version:1.0
 */
@Data
public class DeviceQueryResDTO implements Serializable{

    /**
     * 服务端生成的唯一设备ID
     */
    private String xyid;

}
