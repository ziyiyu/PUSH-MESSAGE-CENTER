package com.xinyan.message.center.common.utils;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.google.common.base.Throwables;
import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * DES加密 解密算法
 *
 * @author songhongyu
 */
@Slf4j
public class DesUtil {

    private final static String DES = "DES";
    private final static String ENCODE = "GBK";

    /**
     * Description 根据键值进行加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) {
        try {
            //用0在前面补足8位
            key = String.format("%8s", key).replaceAll(" ", "0");
            byte[] bt = encrypt(data.getBytes(ENCODE), key.getBytes(ENCODE));
            String strs = new BASE64Encoder().encode(bt);
            return strs;
        } catch (Exception e) {
            log.error("error encrypt Exception:{}", Throwables.getStackTraceAsString(e));
            // 出错不中断业务
            return null;
        }
    }

    /**
     * 根据键值进行解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) {
        try {
            //用0在前面补足8位
            key = String.format("%8s", key).replaceAll(" ", "0");
            if (data == null)
                return null;
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);
            byte[] bt = decrypt(buf, key.getBytes(ENCODE));
            return new String(bt, ENCODE);
        } catch (Exception e) {
            log.error("error decrypt Exception:{}", Throwables.getStackTraceAsString(e));
            // 出错不中断业务
            return null;
        }

    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) {
        try {
            // 生成一个可信任的随机数源
            SecureRandom sr = new SecureRandom();

            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key);

            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);

            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(DES);

            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new ServiceException(ErrorMsgEnum.PARAM_ENCRYPT_ERROR);
        }

    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) {
        try {
            // 生成一个可信任的随机数源
            SecureRandom sr = new SecureRandom();

            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key);

            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);

            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(DES);

            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new ServiceException(ErrorMsgEnum.PARAM_DECRYPT_ERROR);
        }
    }

    public static void main(String[] args) {
        String enCode = encrypt("11808151957525530202101", "1123111213132132");
        System.out.println(enCode);
        String deCode = decrypt(enCode, "1123111213132132");
        System.out.println(deCode);

    }

}
