package com.xinyan.message.center.common.exception;

import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import lombok.Getter;

/**
 * Module Desc:自定义业务处理异常
 * User: gambo
 * DateTime: 2018/7/23
 */
public class ServiceException extends RuntimeException {

    @Getter
    private String errCode;

    @Getter
    private String errMsg;

    @Getter
    private ErrorMsgEnum errorMsgEnum;

    public ServiceException(String errCode) {
        this.errCode = errCode;
    }

    public ServiceException(String errCode, Throwable throwable) {
        super(throwable);
        this.errCode = errCode;
    }

    public ServiceException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
        this.errMsg = message;
    }


    public ServiceException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum.getMsg());
        this.errorMsgEnum=errorMsgEnum;
        this.errCode = errorMsgEnum.getCode();
        this.errMsg = errorMsgEnum.getMsg();
    }

    public ServiceException(String errCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errCode = errCode;
        this.errMsg = message;
    }

}
