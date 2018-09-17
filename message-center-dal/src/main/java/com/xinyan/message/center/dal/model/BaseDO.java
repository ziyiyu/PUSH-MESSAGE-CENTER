package com.xinyan.message.center.dal.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * <ul>
 *  <li>数据基础模型</li>
 *  <li>author:beal; date:2018-09-14</li>
 * </ul>
 */
@Data
@ToString(exclude = {"createdAt","createdBy","updatedAt","updatedBy"})
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 8391442196301842606L;

    /** 数据库主键 */
    private Long id;

    /** 创建时间 */
    private Date createdAt;

    /** 创建人 */
    private String createdBy;

    /** 更新时间 */
    private Date updatedAt;

    /** 最后更新人 */
    private String updatedBy;

}
