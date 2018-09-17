package com.xinyan.message.center.web.model;

import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@ApiModel(value = "返回结果")
public class Result<T> {

    private static final long serialVersionUID = 8350327877975282483L;
    private Boolean success;
    private T data;
    private String errorCode;
    private String errorMsg;
    private Long timeInMillis;

    public Result() {
        this.timeInMillis = System.currentTimeMillis();
    }

    public Result(T data) {
        this.success = true;
        this.data = data;
        this.timeInMillis = System.currentTimeMillis();
    }

    public Result(String errorCode, String errorMsg) {
        this.success=false;
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
        this.timeInMillis =System.currentTimeMillis();
    }

    public Result(ErrorMsgEnum errorMsgEnum) {
        this.success=false;
        this.errorCode=errorMsgEnum.getCode();
        this.errorMsg=errorMsgEnum.getMsg();
        this.timeInMillis =System.currentTimeMillis();
    }


}