package com.xinyan.message.center.common.utils;

import java.util.Random;

/**
 * Description: 生成新的加密token
 * Author:songhongyu
 * Date:2018/8/9 16:17
 * Version:1.0
 */
public class CreateNewTokenUtils {

    public static String md5Token(String xyId){
        String newToken = xyId + System.currentTimeMillis() + new Random().nextInt(100);
        return MD5Helper.encrypt16(newToken);
    }

    public static void main(String[] args) {
        System.out.println(md5Token("1"));
    }

}
