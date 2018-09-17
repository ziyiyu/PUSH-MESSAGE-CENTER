package com.xinyan.message.center.common.utils;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * Author:songhongyu
 * Date:2018/8/9 20:24
 * Version:1.0
 */
public class MapUtils {

    /**
     * 求Map<K,V>中Key(键)的最小值对应的value
     * @param map
     * @return
     */
    public static Object getMinKeyOfValue(Map<Double, ?> map) {
        if (CollectionUtils.isEmpty(map)){
            return null;
        }
        Set<Double> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return map.get(obj[0]);
    }

}
