package com.xinyan.message.center.facade.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <ul>
 *  <li>用于实例查询传参</li>
 *  <li>author:beal; date:2018-09-06; </li>
 * </ul>
 */
@Data
@ApiModel("实例查询传参")
public class BaseReqDTO implements Serializable{

    /** 串行版本ID*/
    private static final long serialVersionUID = -5526116344026940880L;

    @ApiModelProperty("创建开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    private Date beginCreatedAt;

    @ApiModelProperty("创建结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    private Date endCreatedAt;

    @ApiModelProperty("更新开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    private Date beginUpdatedAt;

    @ApiModelProperty("更新结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    private Date endUpdatedAt;
}
