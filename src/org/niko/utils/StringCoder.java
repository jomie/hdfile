package org.niko.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringCoder {
	
	/**
	 * 解码UTF-8
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String utf82URL(String str) throws UnsupportedEncodingException {
		// 加号替换成空格URL编码
		return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
	}
	
	public static String url2UTF8(String str) throws UnsupportedEncodingException {
		if (str == null)
			return "";
		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
	}
}
