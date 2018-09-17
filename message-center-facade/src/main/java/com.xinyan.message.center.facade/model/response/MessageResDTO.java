/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.facade.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luojitao
 * @version Id: MessageResDTO.java, v 0.1 2018/9/6 17:06 luojitao Exp $$
 */
@Data
@ApiModel("平台端返回消息内容")
public class MessageResDTO implements Serializable {


    private static final long serialVersionUID = 1972280315760917676L;

    @ApiModelProperty("请求编号")
    private String requestId;
}
