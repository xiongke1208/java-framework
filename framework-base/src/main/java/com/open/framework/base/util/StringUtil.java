package com.open.framework.base.util;

import java.util.Base64;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {

	private static Log LOG = LogFactory.getLog(StringUtil.class);

	public static boolean isEmpty(String s) {
		if (s == null || "".equals(s)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取文本的首字母
	 * 
	 * @param words
	 * @return
	 */
	public static String getFirstLetter(String text, String defaultStr) {
		String result = defaultStr;
		if (!isEmpty(text)) {
			if (isEng(text.charAt(0))) {// 英文字符取第一个
				result = text.substring(0, 1).toUpperCase();
			} else {
				String[] pinyinAarr = PinyinHelper
						.toHanyuPinyinStringArray(text.charAt(0));
				if (pinyinAarr != null) {
					result = (pinyinAarr[0].toCharArray()[0] + "")
							.toUpperCase();
				}
			}
		}
		return result;
	}

	/**
	 * 是否英文字母
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isEng(char c) {
		boolean re = false;
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
			re = true;
		}
		return re;
	}

	/**
	 * 对字符串base64编码
	 * 
	 * @param s
	 * @param encoding
	 * @return
	 */
	public static String base64Encode(String s, String encoding) {
		String result = s;
		try {
			byte[] b = Base64.getEncoder().encode(s.getBytes(encoding));
			result = new String(b, encoding);
		} catch (Exception e) {
			LOG.error("字符串base64编码异常:" + s + ":" + encoding, e);
		}

		return result;
	}

	/**
	 * 对字符串base64编码
	 * 
	 * @param s
	 * @param encoding
	 * @return
	 */
	public static String base64Decode(String s, String encoding) {
		String result = s;
		try {
			byte[] b = Base64.getDecoder().decode(s.getBytes(encoding));
			result = new String(b, encoding);
		} catch (Exception e) {
			LOG.error("字符串base64编码异常:" + s + ":" + encoding, e);
		}

		return result;
	}

	private static boolean isNotEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if(isEmpty(source)) {
			return source;
		}
		
		int len = source.length();
		StringBuilder buf = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isNotEmojiCharacter(codePoint)) {
				buf.append(codePoint);
			}
		}
		return buf.toString();
	}

	public static boolean objIsNull(Object obj) {
		return obj==null;
	}
	
	public static boolean objIsNotNull(Object obj) {
		return obj!=null;
	}
}
