package com.atgugui.common.utils;

import java.security.MessageDigest;
import java.util.Random;

import org.apache.shiro.codec.Hex;

public class SaltUtil {
	public static String generate(String password , String salt) {
		
		password = md5Hex(password + salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			// 取MD5后密文的第一位
			cs[i] = password.charAt(i / 3 * 2);

			// 取盐值的第一位
			char c = salt.charAt(i / 3);
			cs[i + 1] = c;

			// 取MD5后密文的第二位
			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
		}

		// 生成的密文包括MD5的密文和盐值，顺序为一位密文，一位盐值，一位密文
		return new String(cs);
	}
	
	/** 
	* 获取十六进制字符串形式的MD5摘要 
	*/ 
	public static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/** 获取盐值
	 * @return
	 */
	public static String getSalt() {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(getSalt());
	}
}
