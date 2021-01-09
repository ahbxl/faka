package com.card.utils;

import java.text.SimpleDateFormat;
import java.util.Random;

public class RandomUtil {
    //生成随机数字和字母,
    public static String getStringRandom(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        String date = simpleDateFormat.format(System.currentTimeMillis());
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString() + date;
    }
}