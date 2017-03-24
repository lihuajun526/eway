package com.qheeshow.eway.common.util;

import org.apache.http.Header;

import java.io.File;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lihuajun on 2016/9/12.
 */
public class StrUtil {

    //获得随机数
    public static String getNoncestr() {
        return "Wm3WZYTPz0wzccnW";
    }

    public static String sha1(String decrypt) throws DigestException {
        try {
            //指定sha1算法
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            //获取字节数组
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString().toLowerCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new DigestException("签名错误！");
        }
    }

    public static String[] getFilePath(String fileType) {
        String[] strs = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        StringBuffer sb1 = new StringBuffer(Config.get("filter." + fileType + ".storage.path"));
        StringBuffer sb2 = new StringBuffer(Config.get("filter." + fileType + ".web.path"));
        strs[0] = sb1.append(File.separator).append(sdf.format(date)).toString();
        strs[1] = sb2.append(File.separator).append(sdf.format(date)).toString();
        return strs;
    }

    public static String getOrderno() {
        return String.valueOf(System.nanoTime());
    }

    public static String handle(String mobile) {
        if (mobile.contains("86")) {
            return mobile;
        } else {
            return "86" + mobile;
        }
    }

    public static String md5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(str.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

}
