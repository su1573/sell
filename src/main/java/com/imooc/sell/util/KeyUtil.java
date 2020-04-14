package com.imooc.sell.util;

import java.util.Random;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 18:31
 * @Author: Mr.SU
 * @Description:
 */
public class KeyUtil {
    /**
     * @param "[] "
     * @Description:生成主键,synchronized防止主键一样
     * @Return: java.lang.String
     * @Author: Mr.Su
     * @Date: 2020/4/6 0006 18:31
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();

        //生成6为随机数
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

}
