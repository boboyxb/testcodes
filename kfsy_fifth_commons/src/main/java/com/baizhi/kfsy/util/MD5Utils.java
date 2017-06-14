package com.baizhi.kfsy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5工具方法的封装
 * @author Administrator
 *
 */
public class MD5Utils {

	/**
	 * 用来生成md5加密结果
	 * @param password  加密内容
	 * @return
	 */
	public static String getMd5Code(String password){
		try {
			//java.secutiry
			MessageDigest messageDigest = MessageDigest.getInstance("md5") ;
			//加密
			byte[] digest = messageDigest.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			//byte转为16进制长度是32位字符串  byte  -128~~~127    0~~255
			for (byte b : digest) {  //0x0-0  0x1-1   0xa-10 0xf 15 0x10-16 0x11-17    
				int c = b & 0xff;
				if(c<16){
					sb.append("0");
				}
				sb.append(Integer.toHexString(c));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		
		String md5Code = getMd5Code("123456");
		System.out.println(md5Code);
		
		
	}
}
