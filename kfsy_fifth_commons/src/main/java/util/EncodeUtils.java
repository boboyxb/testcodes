package util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeUtils {
	public static String getEncoded(String str,String charset){
		String encode=null;
		try {
			encode=URLEncoder.encode(str, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encode;
	}
	public static String getEncoded(String str){
		String encoded = getEncoded(str, "UTF-8");
		return encoded;
	}
}
