package com.xinyan.message.center.common.exception;

import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import lombok.Getter;

/**
 * Module Desc:自定义Manager异常
 * User: gambo
 * DateTime: 2018/7/23
 */
public class ManagerException extends RuntimeException{

    @Getter
    private String errCode;

    @Getter
    private String errMsg;

    @Getter
    private ErrorMsgEnum errorMsgEnum;

    public ManagerException(String errCode) {
        this.errCode = errCode;
    }


    public ManagerException(String errCode, Throwable throwable) {
        super(throwable);
        this.errCode = errCode;
    }

    public ManagerException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
        this.errMsg = message;
    }

    public ManagerException(ErrorMsgEnum errorMsgEnum) {
        super(errorMsgEnum.getMsg());
        this.errorMsgEnum=errorMsgEnum;
        this.errCode = errorMsgEnum.getCode();
        this.errMsg = errorMsgEnum.getMsg();
    }

    public ManagerException(ErrorMsgEnum errorMsgEnum,Throwable throwable) {
        super(throwable);
        this.errorMsgEnum=errorMsgEnum;
        this.errCode = errorMsgEnum.getCode();
        this.errMsg = errorMsgEnum.getMsg();
    }

    public ManagerException(String errCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errCode = errCode;
        this.errMsg = message;
    }

}
