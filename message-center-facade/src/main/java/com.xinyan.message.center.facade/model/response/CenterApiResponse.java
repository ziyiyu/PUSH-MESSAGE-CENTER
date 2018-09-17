package com.xinyan.message.center.facade.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * <ul>
 * <li>统一返回</li>
 * <li>author:beal; date:2018/9/17</li>
 * </ul>
 */
@Data
public class CenterApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1600714809209858817L;
    private Boolean success;
    private String errorCode;
    private String errorMsg;
    private T data;
    private Long timeInMillis;
}