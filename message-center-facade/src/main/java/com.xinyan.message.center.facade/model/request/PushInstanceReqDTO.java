package com.xinyan.message.center.facade.model.request;

import com.xinyan.message.center.facade.model.BaseReqDTO;
import com.xinyan.message.center.facade.validate.JsrGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <ul>
 * <li>服务器实例参数</li>
 * <li>author:beal; date:2018/9/17</li>
 * </ul>
 */
@Data
@ApiModel("服务器实例信息")
public class PushInstanceReqDTO extends BaseReqDTO implements Serializable{

    /**  串行版本ID */
    private static final long serialVersionUID = 5876841045435021752L;

    /** 编号 */
    @ApiModelProperty("id")
    @NotNull(message = "id不能为空",groups = {JsrGroup.Delete.class,JsrGroup.MUpdate.class})
    private Integer id;

    /** 实例ID */
    @ApiModelProperty("实例ID")
    @NotBlank(message = "实例ID不能为空",groups = { JsrGroup.Add.class, JsrGroup.Update.class})
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

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createdAt;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createdBy;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Date updatedAt;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updatedBy;

}
