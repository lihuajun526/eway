package com.qheeshow.eway.common.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by lihuajun on 2017/5/2.
 */
public class EncodeUtil {

    public static String getEncoding(String str) {
        String encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    public static void main(String[]args){
        //System.out.print(EncodeUtil.getEncoding(str));
        try {
            System.out.println("中文".getBytes("GBK"));
            System.out.println("中文".getBytes("ISO8859_1"));
            System.out.println(new String("中文".getBytes()));
            System.out.println(new String("中文".getBytes(), "UTF-8"));
            System.out.println(new String("中文".getBytes(), "ISO8859_1"));
            System.out.println(new String("中文".getBytes("ISO8859_1"),"ISO8859_1"));
            System.out.println(new String("中文".getBytes("GB2312"), "GB2312"));
            System.out.println(new String("中文".getBytes("GB2312"), "ISO8859_1"));
            System.out.println(new String("中文".getBytes("ISO8859_1")));
            System.out.println(new String("中文".getBytes("ISO8859_1"), "GB2312"));
            System.out.println(new String("中文".getBytes("ISO8859_1"), "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
