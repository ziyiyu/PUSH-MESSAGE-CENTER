package com.xinyan.message.center.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Module Desc:显示前端的错误
 * User: gambo
 * DateTime: 2018/7/23
 */
@Getter
@AllArgsConstructor
public enum ErrorMsgEnum {

    FAILD("9999","处理失败"),

    SYSTEM_ERROR("SYSTEM_ERROR", "系统内部错误"),
    PARAM_ENCRYPT_ERROR("PARAM_ENCRYPT_ERROR", "参数加密失败"),
    PARAM_DECRYPT_ERROR("PARAM_DECRYPT_ERROR", "参数解密失败"),


    REDIS_FAIL("REDIS_FAIL", "存储到redis失败"),

    INSTANCE_GET_ERROR ("INSTANCE_GET_ERROR", "获取实例异常"),
    INSTANCE_ADD_ERROR ("INSTANCE_ADD_ERROR", "插入连接实例异常"),
    TOKEN_APPROVE_ERROR ("TOKEN_APPROVE_ERROR", "token认证异常"),
    INVALID_SIGN("INVALID_SIGN", "无效的sign信息"),
    WRONG_TOKEN("WRONG_TOKEN", "传入的token不正确"),
    MU_INFO_FAIL("MU_INFO_FAIL", "获取商户或者用户信息失败"),

    PARAMETER_ERROR("PARAMETER_ERROR", "参数错误"),
    PARAMETER_MISSING("PARAMETER_MISSING", "缺少参数"),

    DATA_ERROR("DATA_ERROR", "数据错误"),
    DATA_EXITS("DATA_EXITS", "数据已存在"),
    DATA_ADD_FAIL("DATA_ADD_FAIL", "添加数据失败"),
    DATA_DEL_FAIL("DATA_DEL_FAIL", "删除数据失败"),
    DATA_QUERY_FAIL("DATA_QUERY_FAIL", "查询数据失败"),
    DATA_UPDATE_FAIL("DATA_UPDATE_FAIL", "更新数据失败"),
    DATA_CHECK_FAIL("DATA_CHECK_FAIL", "数据验证失败"),
    DATA_INVALID("DATA_INVALID", "数据库存在脏数据"),

    FIlE_UPLOAD_FAILED("FIlE_UPLOAD_FAILED","上传文件失败"),
    FIlE_NULL("FIlE_NULL","上传文件为空"),
    FIlE_TYPE_NULL("FIlE_TYPE_NULL","上传文件类型为空"),
    FIlE_TYPE_ERROR("FIlE_TYPE_ERROR","上传文件类型错误"),

    DEVICE_TYPE_ERROR("DEVICE_TYPE_ERROR","设备格式错误"),
    TOPIC_TYPE_ERROR("TOPIC_TYPE_ERROR","频道格式错误"),
    KAFKA_SEND_ERROR("KAFKA_SEND_ERROR","KAFKA发送失败"),
    SEND_MESSAGE_ERROR("SEND_MESSAGE_ERROR","消息推送失败");


    private String code;
    private String msg;
}
