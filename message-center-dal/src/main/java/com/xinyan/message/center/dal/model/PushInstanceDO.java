package com.xinyan.message.center.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Module Desc:服务器实例返回DTO
 * User: gambo
 * DateTime: 2018/8/8
 */
@Data
@ApiModel("服务器实例信息")
public class PushInstanceDO extends BaseDO implements Serializable{

    /**
     * 串行版本ID
     */
    private static final long serialVersionUID = -5526116344026940880L;

    /** 实例ID */
    @ApiModelProperty("实例ID")
    private String instanceId;

    /** 客户端数量 */
    @ApiModelProperty("客户端数量")
    private String clientSum;

    /** CPU使用率 */
    @ApiModelProperty("CPU使用率")
    private String cpu;

    /** 内存使用率 */
    @ApiModelProperty("内存使用率")
    private String mem;

    /** 负载使用率 */
    @ApiModelProperty("负载使用率")
    private String load;

    /** IP端口 */
    @ApiModelProperty("IP端口")
    private String ipPort;

    /** 服务器状态 */
    @ApiModelProperty("服务器状态")
    private String serverStatus;

    /** 父节点信息 */
    @ApiModelProperty("父节点信息")
    private String parentNode;

    /** 创建开始时间 */
    @ApiModelProperty("创建开始时间")
    private Date beginCreatedAt;

    /** 创建结束时间 */
    @ApiModelProperty("创建结束时间")
    private Date endCreatedAt;

    /** 更新开始时间 */
    @ApiModelProperty("更新开始时间")
    private Date beginUpdatedAt;

    /** 更新结束时间 */
    @ApiModelProperty("更新结束时间")
    private Date endUpdatedAt;
}
