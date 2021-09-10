package com.zzy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ Description: MD5加密工具
 * @ author zhangziyao
 * @ date 2021/9/6 20:19
 */
public class MD5Utils {
    public static String encrypt(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        // 将结果存入数据库
        System.out.println(encrypt("root"));
    }
}
