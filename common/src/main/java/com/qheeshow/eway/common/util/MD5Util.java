package com.qheeshow.eway.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	/**
	 * 取一个字符窜的MD5摘要，并表示为一个字符串
	 */
	public  static String MD5(String s) {
		
		if (s==null||s.length()==0){
			return null;
		}
		char hexDigits[] = { 'A', '1', 'B', '3', 'C', '5', 'D', '7', 'E','9', 'F', '0', 'G', '2', 'H', '4' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 比较字符串str的MD5值是否与字符串md5Str的值相同
	 * @param str
	 * @param md5Str
	 * @return
	 */
	public static boolean MD5Validate(String str,String md5Str){
		
		if (md5Str==null||md5Str.length()==0){
			return false;
		}
		if(md5Str.equals(MD5(str))){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static void main(String[] args) {

		MessageDigest md5= null;
		String sourceStr = "123";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
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
			String result = buf.toString();
			System.out.println("MD5(" + sourceStr + ",32) = " + result);
			System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}


	}
}
