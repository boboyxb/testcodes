package util;

import java.util.Random;

/**
 * 随机盐的工具类
 * @author Administrator
 *
 */
public class SaltUtils {
	
	/**
	 * 生成随机盐的方法
	 * @param n
	 * @return
	 */
	public static String  getSalt(int n){
		char[] code ="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+-=".toCharArray();
		//随机数
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		//StringBuilder 线程不安全  轻量级    效率高
		for (int i = 0; i < n; i++) {
			sb.append(code[random.nextInt(code.length)]);
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		String salt = getSalt(8);
		System.out.println(salt);
	}
	
}
