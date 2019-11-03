package com.hwy.shipyard.utils;

import java.util.Random;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
public class KeyUtils {

    /**
     * 随机生成id
     * @return 随机生成的id
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer a = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(a);
    }
}
