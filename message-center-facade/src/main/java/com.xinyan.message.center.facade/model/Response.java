package com.xinyan.message.center.facade.model;

import com.xinyan.message.center.facade.model.response.DeviceQueryResDTO;
import lombok.Getter;
import lombok.ToString;

/**
 * Description:
 * Author:songhongyu
 * Date:2018/8/15 15:16
 * Version:1.0
 */
@Getter
@ToString
public class Response {

    private boolean success;
    private DeviceQueryResDTO result;
    private String errorCode;
    private String errorMsg;

}
