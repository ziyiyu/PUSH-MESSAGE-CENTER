/**
 * Corearchi.com Inc.
 * Copyright (c) 2017-2018 All Rights Reserved.
 */
package com.xinyan.message.center.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author luojitao
 * @version Id: CommonUtils.java, v 0.1 2018/9/10 11:03 luojitao Exp $$
 */
public class CommonUtils {

    final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z'};

    /**
     * 随机ID生成器，由数字、小写字母和大写字母组成
     *
     * @param size
     * @return
     */
    public static String getuuid(int size) {
        Random random = new Random();
        char[] cs = new char[size];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = digits[random.nextInt(digits.length)];
        }
        return new String(cs);
    }

    /**
     * 正则匹配
     * @param content
     * @param pattern
     * @return
     */
    public static boolean matchs(String content,String pattern){
        return Pattern.matches(pattern,content);
    }

    /**
     * 获取msgID
     *
     * @return
     */
    public static String getmsgId() {

        String msgId = "";
        //获取当前时间戳
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String temp = sf.format(new Date());
        //获取9位UUID
        String uuid = getuuid(9);
        msgId = temp + "J" + uuid;
        return msgId;
    }

    public static Map parseToMap(String json){
        Map map= JSONObject.parseObject(json);
        return map;
    }

    public static String pareseToString(Map map){
        return JSONObject.toJSONString(map);
    }
}
