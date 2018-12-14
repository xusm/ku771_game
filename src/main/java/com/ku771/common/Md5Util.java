package com.ku771.common;

import java.security.MessageDigest;


/**   
 * @title：Md5加密
 * @class：Md5Util   
 * @description：   Md5加密
 * @author：Eric
 * @date：2018年12月6日
 */
public class Md5Util {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteToHexString(byte b){

        int n = b;
        if(n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;

        return hexDigits[d1] + hexDigits[d2];
    }

    private static String byteArrayToHexString(byte bys[]){
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < bys.length; i++) {
            resultString.append(byteToHexString(bys[i]));
        }
        return resultString.toString();
    }

    /**
     * MD5加密
     * @param origin    源字符串
     * @param charsetCode  字符编码
     * @return
     */
    public static String md5Encode(String origin,String charsetCode){

        String encodedResult = "";

        try {
            encodedResult = new String(origin);
            MessageDigest md = MessageDigest.getInstance("md5");
            if(charsetCode == null || "".equals(charsetCode)){
                encodedResult = byteArrayToHexString(md.digest(encodedResult.getBytes()));
            }else{
                encodedResult = byteArrayToHexString(md.digest(encodedResult.getBytes(charsetCode)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encodedResult.toUpperCase();
    }

    /**
     * 基于UTF-8编码的Md5加密
     * @param origin
     * @return
     */
    public static String md5EncodeUTF8(String origin){
        return md5Encode(origin,"UTF-8");
    }
}