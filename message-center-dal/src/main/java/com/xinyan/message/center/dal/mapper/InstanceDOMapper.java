package com.xinyan.message.center.dal.mapper;

import com.xinyan.message.center.dal.model.PushInstanceDO;

import java.util.List;

/**
 * Module Desc: 消息推送服务器实例信息数据库层
 * User: beal
 * DateTime: 2018/9/17
 */
public interface InstanceDOMapper {

    /**
     * 查询服务器实例信息列表
     *
     * @return list
     */
    PushInstanceDO selectBestInstance();

    /**
     * 新增服务器实例信息
     *
     * @param record      新增服务器实例信息
     * @return            返回结果集
     */
    int addBySelective(PushInstanceDO record);

    /**
     * 删除服务器实例信息
     *
     * @param record      删除服务器实例信息
     * @return            返回结果集
     */
    int deleteByPrimaryKey(PushInstanceDO record);

    /**
     * 查询服务器实例信息
     *
     * @param record      查询服务器实例信息
     * @return            返回结果集
     */
    List<PushInstanceDO> selectBySelective(PushInstanceDO record);

    /**
     * 根据INDSTANCE_ID查询服务器实例信息
     *
     * @param record      服务器实例ID
     * @return            返回结果集
     */
    PushInstanceDO selectByInstanceId(String record);

    /**
     * 更新服务器实例信息
     *
     * @param record      更新服务器实例信息
     * @return            返回结果集
     */
    int updateByPrimaryKey(PushInstanceDO record);

    /**
     * 更新服务器实例信息By instanceId
     *
     * @param record      更新服务器实例信息
     * @return            返回结果集
     */
    int updateByInstanceId(PushInstanceDO record);

}