/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.common.constant;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luojitao
 * @version Id: Constant.java, v 0.1 2018/9/12 11:18 luojitao Exp $$
 */
public class Constant {

    /** 常量LOGID */
    public static final String LOG_UUID="LOG_UUID";

    /** topics key*/
    public static final String TOPICS="topics";

    /** 设备列表关键字*/
    public static final String DEVICES="devices";

    /** msgId */
    public static final String MSGID="msgId";

    /** requestId */
    public static final String requestId="requestId";

    /**  操作类型 */
    public static final String OPERATETYPE="operateType";

    /**  验证频道正则 */
    public static final String PATTERN="^[a-zA-Z0-9/]+$";

    /** 验证设备正则 */
    public static final String DEVICE_PATTERN="^[a-zA-Z0-9]+$";

    /** kafka topic基本规则 */
    public static final String KAFKA_TOPIC_BASIC="PUSH-MESSAGE-CENTER-";

    /** 全局关键key */
    public static final String GLOBAL="GLOBAL";

    /** 频道数量阈值 */
    public static final AtomicInteger CHANNEL_MAX=new AtomicInteger(5);

    /** 成功 */
    public static final String RESULT_SUCCESS="1";

    /** 频道格式错误 */
    public static final String RESULT_TOPIC_ERROR="2";

    /** 设备格式错误 */
    public static final String RESULT_DEVICE_ERROR="3";

    /** kafka 连线异常*/
    public static final String RESULT_KAFKA_ERROR="4";

}
