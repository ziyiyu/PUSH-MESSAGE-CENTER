package com.xinyan.message.center.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * Description:
 * User: songhongyu
 * Date: 2018/7/30
 * Time: 15:15
 */
@Slf4j
public class ObjectUtils {

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence){
            return ((CharSequence) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 根据传入的对象和get方法获取相关属性值
     * @param obj 对象
     * @param objClass 对象所属的类
     * @param methodName 方法名称
     * @param clazz 返回值类型
     * @return T 返回值
     **/
    public static <T> T getFieldValue(Object obj,Class objClass,String methodName,Class<T> clazz) {
        T ret = null;
        try {
            if (!isEmpty(obj) && StringUtils.isNotBlank(methodName) && objClass!=Object.class) {
                Method method = null;
                method = objClass.getMethod(methodName, new Class[] {});
                Object value = method.invoke(obj, new Object[] {});
                ret = clazz.cast(value);
            }

        } catch (NoSuchMethodException e) {
            return getFieldValue(obj,objClass.getSuperclass(),methodName,clazz);
        } catch (Exception e) {
            log.error("获取指定属性错误！obj:{};method:{}",obj,methodName,e);
        }
        return ret;
    }

    /**
     * 根据传入的对象和set方法设置相关属性值
     * @param obj 对象
     * @param objClass 对象所属的类
     * @param methodName 方法名称
     * @param value 属性值
     **/
    public static void setFieldValue(Object obj,Class objClass,String methodName,Object value) {
        try {
            if (!isEmpty(obj) && StringUtils.isNotBlank(methodName) &&  objClass!=Object.class) {
                Method method = objClass.getMethod(methodName, new Class[]{value.getClass()});
                objClass.getMethods();
                method.invoke(obj, value);
            }
        } catch (NoSuchMethodException e) {
            setFieldValue(obj, objClass.getSuperclass(), methodName, value);
        } catch (Exception e) {
            log.error("设置相关属性失败！obj:{};method:{};value:{}", obj, methodName, value, e);
        }
    }

}
