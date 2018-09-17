/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luojitao
 * @version Id: OperateEnum.java, v 0.1 2018/9/13 17:29 luojitao Exp $$
 */
@Getter
@AllArgsConstructor
public enum  OperateEnum {

    SUB_MSG("SUB_MSG","订阅消息"),
    UNSUB_MSG("UNSUB_MSG","取消订阅"),
    PUB_MSG("PUB_MSG","推送消息");
    private String code;

    private String msg;
}
