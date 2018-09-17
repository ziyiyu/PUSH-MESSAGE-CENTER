package com.xinyan.message.center.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * redis 帮助类
 * Created by Lijing on 2017/8/1.
 */
@Slf4j
@Component
public class RedisManagerHelp {
    /**
     * redis锁超时时间
     */
    private static final Long LOCK_TIME_OUT = 60L;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static Random random = new Random();
    /**
     * redis锁
     */
    private final static String LOCK_KEY = "MESSAGE_CENTER_API_LOCK";

    /**
     * 获取redis锁key
     *
     * @return redis 锁
     */
    private String getLockKey() {
        return LOCK_KEY;
    }

    /**
     * 获取redis锁value
     *
     * @return 当前时间+随机数value
     */
    public String getLockValue() {
        return System.currentTimeMillis() + ":" + random.nextInt(100);
    }


    /**
     * 设置redis锁
     *
     * @param key   锁key
     * @param value 锁value
     * @return true 获取锁成功 false 获取锁失败
     */
    private boolean setNX(final String key, final String value, final Long timeOut) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(serializer.serialize(key), serializer.serialize(value));
                    if (success) {
                        connection.setEx(serializer.serialize(key), timeOut, serializer.serialize(value));
                    }
                    connection.close();
                    return success;
                }
            });
        } catch (Exception e) {
            log.error("设置redis锁[]失败 ", key);
        }
        return obj != null ? (Boolean) obj : false;
    }

    /**
     * 获取redis 锁
     *
     * @param value 锁值(删除锁时校验,避免误删)
     * @return true 获取锁成功 false 获取锁失败
     */
    public boolean getMainLock(String value) {
        return setNX(getLockKey() + ":MAIN", value, LOCK_TIME_OUT);
    }

    /**
     * 获取redis 锁
     *
     * @param value 锁值(删除锁时校验,避免误删)
     * @return true 获取锁成功 false 获取锁失败
     */
    public boolean getLock(String value) {
        return setNX(getLockKey(), value, LOCK_TIME_OUT);
    }

    /**
     * 获取 redis 锁
     *
     * @return true 获取锁成功 false 获取锁失败
     */
    public boolean getLock(String key, String value, Long timeOut) {
        return setNX(key, value, timeOut);
    }

    /**
     * 获取redis 锁
     *
     * @return true 获取锁成功 false 获取锁失败
     */
    public boolean removeLock(String value) {
        String key = getLockKey();
        return removeLock(key, value);
    }

    /**
     * 获取redis 锁
     *
     * @return true 获取锁成功 false 获取锁失败
     */
    public boolean removeMainLock(String value) {
        String key = getLockKey() + ":MAIN";
        return removeLock(key, value);
    }

    /**
     * 获取redis 锁
     *
     * @return true 获取锁成功 false 获取锁失败
     */
    public boolean removeLock(String key, String value) {
        String redisValue = redisTemplate.opsForValue().get(key);
        if (value.equals(redisValue)) {
            redisTemplate.delete(key);
            return true;
        }
        log.error("锁key：{} redis值[{}],原值[{}]已发生变化无法解锁", key, redisValue, value);
        return false;
    }

    /**
     * 新增redis 数据
     *
     * @param key     键
     * @param obj     值
     * @param timeOut 超时时间(s)
     * @return true 新增成功 false 新增失败
     */
    public boolean insertObj(String key, Object obj, Long timeOut) {
        try {
            String json = JSON.toJSONString(obj);
            redisTemplate.opsForValue().set(key, json, timeOut, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("redis新增失败，原因:", e);
        }
        return false;
    }

    /**
     * 新增redis 不会转换成json数据
     *
     * @param key     键
     * @param str     值
     * @param timeOut 超时时间(s)
     * @return true 新增成功 false 新增失败
     */
    public boolean insertString(String key, String str, Long timeOut) {
        try {
            redisTemplate.opsForValue().set(key, str, timeOut, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("redis新增失败，原因:", e);
        }
        return false;
    }

    /**
     * 获取redis中数据
     *
     * @param key    键
     * @param tClass 获取的值需要转换的对象
     * @return redis中存储的值  null 为不存在或获取失败
     */
    public <T> T getObj(String key, Class<T> tClass) {
        try {

            String json = redisTemplate.opsForValue().get(key);
            return JSONObject.parseObject(json, tClass);
        } catch (Exception e) {
            log.error("redis获取失败，原因:", e);
        }
        return null;
    }

    /**
     * 获取redis中数据
     *
     * @param key 键
     * @return redis中存储的值  null 为不存在或获取失败
     */
    public String getValue(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("redis获取失败，原因:", e);
        }
        return null;
    }

    /**
     * 删除redis中数据
     *
     * @param key 键
     * @return true 删除成功 false 删除失败
     */
    public boolean delObj(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            log.error("redis获取失败，原因:", e);
        }
        return false;
    }

    /**
     * redis 自增长
     *
     * @param keyEnum 关键字
     */
    public Long autoIncrement(final String keyEnum) {

        Long result = redisTemplate.execute((RedisConnection connection) -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(keyEnum);
            Long num = connection.incr(redisKey);
            log.debug("keyEnum:{} num:{}", keyEnum, num);
            return num;
        });

        log.debug("incr response：{}", result);
        return result;
    }

    /**
     * 判断redis是否存在key
     *
     * @param key key
     * @return true 存在 false 不存在
     */
    public boolean exitsKey(String key) {
        String string = redisTemplate.opsForValue().get(key);
        return !StringUtils.isBlank(string);
    }

}