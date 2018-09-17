package com.xinyan.message.center.common.utils;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

/**
 * Aes算法加解密
 *
 * @author yingmuhuadao
 * @version 5.0
 * @date 2018/4/2
 */
@Slf4j
public class AesUtil {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     * PKCS5Padding填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final String IV_STRING = "DEVICE-AES000000";

    /**
     * 使用AES算法进行加密
     * @param source 加密源
     * @param key 秘钥
     * @return 密文
     */
    public static String encrypt(String source, String key){
        try {
            if (key == null || "".equals(source)) {
                return null;
            }
            byte[] keyBytes = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivParameterSpec);
            byte[] encrypted = cipher.doFinal(source.getBytes(Charset.forName("UTF-8")));
            return Base64.encode(encrypted);
        } catch (Exception e) {
            log.warn("encrypt Exception source {}, key {}",source,key);
            throw new ServiceException(ErrorMsgEnum.PARAM_ENCRYPT_ERROR);
        }
    }

    /**
     *  AES算法解密
     * @param source 密文
     * @param key 秘钥
     * @return 明文
     */
    public static String decrypt(String source, String key) {
        try {
            if (key == null || "".equals(source)) {
                return null;
            }
            byte[] keyBytes =key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,skeySpec,ivParameterSpec);
            byte[] sourceBytes = new BASE64Decoder().decodeBuffer(source);
            byte[] original = cipher.doFinal(sourceBytes);
            return new String(original,Charset.forName("UTF-8"));
        } catch (Exception e) {
            log.warn("decrypt Exception source {}, key {}",source,key);
            throw new ServiceException(ErrorMsgEnum.PARAM_DECRYPT_ERROR);
        }
    }


    /**
     * 使用AES算法进行加密
     * @param source 加密源
     * @param key 秘钥
     *
     * @return 密文
     */
    public static String encryptSign(String source, String key){

        try {
            // 密文 + 日期
            String aesSource = source + ":" + DateUtil.getCurrent();
            if(key.length()<=16){
                // key 补位处理 不足16位补零
                key = Strings.padStart(key, 16, '0');
            }else {
                // key 补位处理 不足16位补零
                key = key.substring(0,16);
            }

            // 加密
            String sign = encrypt(aesSource,key);
            return sign;
        } catch (Exception e) {
            log.error("error encryptSign Exception:{}", Throwables.getStackTraceAsString(e));
            // 出错不中断业务
            return null;
        }
    }

    /**
     * 使用AES算法进行解密
     * @param sign 加密源
     * @param key 秘钥
     *
     * @return 密文
     */
    public static String decryptSign(String sign, String key){

        try {
            if(key.length()<=16){
                // key 补位处理 不足16位补零
                key = Strings.padStart(key, 16, '0');
            }else {
                // key 补位处理 不足16位补零
                key = key.substring(0,16);
            }

            // 第一次解密
            String source = decrypt(sign,key).split(":")[0];

            return source;
        } catch (Exception e) {
            log.error("error decryptSign Exception:{}", Throwables.getStackTraceAsString(e));
            // 出错不中断业务
            return null;
        }
    }

    public static void main(String[] args) throws Exception {

        String sign = encryptSign("11808200957090089111101","8000421685");
        System.out.println("sign:"+sign);
        String source = decryptSign(sign,"8000421685");

        System.out.println("source:"+source);
    }

}
