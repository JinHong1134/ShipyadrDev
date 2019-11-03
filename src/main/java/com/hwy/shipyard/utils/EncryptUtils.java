package com.hwy.shipyard.utils;

import java.security.MessageDigest;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
public class EncryptUtils {

    /**
     * sha加密
     * @param inputStr 待加密的字符串
     * @return
     */
    public static byte[] shaEncrypt(String inputStr) {
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(inputStr.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            return md;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * sha加密后的字节数组转字符串
     * @param md 加密后的字节数组
     * @return
     */
    public static String byteToString(byte[] md) {
        //十六进制对应字符
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        //加密后的字节数组的长度
        int len = md.length;
        char buf[] = new char[len * 2];
        int k = 0;
        //字节转字符串
        for (int i = 0; i < len; i++) {
            byte byte0 = md[i];
            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
            buf[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(buf);
    }

    /**
     * @param inputStr 待加密的字符串
     * @param salt 加的盐
     * @return
     */
    public static String saltEncrypt(String inputStr, String salt) {
        String encryptStr = inputStr + salt;
        byte[] md = EncryptUtils.shaEncrypt(encryptStr);
        String result = EncryptUtils.byteToString(md);
        return result;
    }

}
