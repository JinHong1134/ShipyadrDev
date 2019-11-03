package com.hwy.shipyard.utils;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-29
 */
public class HashUtils {

    public static String genHash(Object object, String preHash) {
        return EncryptUtils.saltEncrypt(object.toString() + preHash, "fkn");
    }
}
