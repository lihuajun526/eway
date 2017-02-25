package com.qheeshow.eway.common.util;

import java.security.MessageDigest;
import java.util.HashMap;

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
		HashMap<String,String> user = new HashMap<String,String>();
		System.out.println(user.isEmpty());
		String a = "浙江省";
		byte[] aa = a.getBytes();
		System.out.println(aa);
		byte[] bb = aa;
		String aaa = new String(bb);
		System.out.println(aaa);
		String b = "浙江2";
		System.out.println(a.indexOf(b));
		String str = MD5("123123");
		System.out.println(str+"==="+MD5Validate("1",str));
		
	}
}
