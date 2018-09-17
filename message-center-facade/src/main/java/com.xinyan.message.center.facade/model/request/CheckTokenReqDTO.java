package com.xinyan.message.center.facade.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Module Desc:缓存token判断信息请求参数
 * User: gambo
 * DateTime: 2018/7/30
 */
@Data
@ApiModel("连接认证请求")
public class CheckTokenReqDTO implements Serializable {

    /** 串行版本ID*/
    private static final long serialVersionUID = -5526116344026940880L;

    /** 连接token */
    @ApiModelProperty("连接token")
    private String token;

    /** 设备指纹sign */
    @ApiModelProperty("设备指纹sign")
    @NotBlank(message = "sign信息不能为空")
    private String dfpSign;

    /** 设备指纹Token */
    @ApiModelProperty("设备指纹Token")
    @NotBlank(message = "dfpToken不能为空")
    private String dfpToken;

    /** 应用标识 */
    @ApiModelProperty("应用标识")
    @NotBlank(message = "appKey不能为空")
    private String appKey;

    /** 商户号 */
    @ApiModelProperty("商户号")
    private String merchantNo;

    /** 新颜ID */
    private String xyId;
}
