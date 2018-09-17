package com.xinyan.message.center.facade.model.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <ul>
 * <li>服务器实例返回信息</li>
 * <li>author:beal; date:2018/9/17</li>
 * </ul>
 */
@Data
@ApiModel("服务器实例返回消息内容")
public class PushInstanceResDTO implements Serializable{

    /**  串行版本ID */
    private static final long serialVersionUID = 4926306577684372352L;

    /** 编号 */
    private Integer id;

    /** 实例ID */
    private String instanceId;

    /** 客户端数量 */
    private String clientSum;

    /** CPU使用率 */
    private String cpu;

    /** 内存使用率 */
    private String mem;

    /** 负载使用率 */
    private String load;

    /** IP端口 */
    private String ipPort;

    /** 服务器状态 */
    private String serverStatus;

    /** 父节点信息 */
    private String parentNode;

    /** 创建时间 */
    private Date createdAt;

    /** 创建人 */
    private String createdBy;

    /** 修改时间 */
    private Date updatedAt;

    /** 修改人 */
    private String updatedBy;

}
