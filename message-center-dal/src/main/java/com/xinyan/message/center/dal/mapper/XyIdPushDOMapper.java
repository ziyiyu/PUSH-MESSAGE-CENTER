package com.xinyan.message.center.dal.mapper;

import com.xinyan.message.center.dal.model.XyIdPushDO;

public interface XyIdPushDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(XyIdPushDO record);

    int insertSelective(XyIdPushDO record);

    XyIdPushDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XyIdPushDO record);

    int updateByPrimaryKey(XyIdPushDO record);
}