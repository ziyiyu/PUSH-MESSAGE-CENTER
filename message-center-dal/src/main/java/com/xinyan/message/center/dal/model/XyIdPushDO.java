package com.xinyan.message.center.dal.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class XyIdPushDO extends BaseDO implements Serializable{

    private static final long serialVersionUID = 8117802338492746544L;

    private String xyId;

    private String instanceId;

    private String token;

    private String ipPort;

}