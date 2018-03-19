package com.ylw.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
/**
 * 文字简单处理工具类
 * @author Nicolas
 * @since 2014年4月1日
 */
public class TextUtil {
	/**
	 * 是否合法字符
	 * 长度 1,30
	 * @author Nicolas
	 * @param text
	 * @return
	 */
	public static boolean isLegalChar(String text){
		Pattern p = Pattern.compile("[A-Za-z0-9\u2E80-\u9FFF\\.\\@_\\-—'~#:]{1,30}");
		return p.matcher(text).find();
	}

	// GENERAL_PUNCTUATION 判断中文的“号
	// CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
	// HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否有中文
	 * 
	 * @param strName
	 * @return
	 */
	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c) == true) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric1(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 去掉括号特殊符号
	 * 
	 * @param demo
	 * @return
	 */
	public static String specialCharReplace(String demo) {
		if (StringUtils.isBlank(demo)) {
			return null;
		}
		return demo.trim().replaceAll("\\(", "").replaceAll("\\)", "")
				.replaceAll("\\（", "").replaceAll("\\）", "");
	}
	/**
	 * 编码汉字专用
	 * @param str
	 * @return
	 */
	public static String encodeStr(String str) {
		String encodeStr = str;
		try {
			if (str != null && !"".equals(str)) {
				encodeStr = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
	/**
	 * 字符型转int型
	 * @param str
	 * @return
	 */
	public static Integer strToint(String str){
		if(str==null||StringUtils.isEmpty(str)){
			return 0;
		}else{
			return Integer.parseInt(str);
		}
	}
	/**
	 * 转换unicode字符
	 * @param unicode
	 * @return
	 */
	public static String decodeUnicode(String unicode){
		try {
			if(StringUtils.isNotBlank(unicode)){
				return decode(unicode.toCharArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unicode;
	}
	private static String decode(char[] in) throws Exception {
		int off = 0;
		char c;
		char[] out = new char[in.length];
		int outLen = 0;
		while (off < in.length) {
			c = in[off++];
			if (c == '\\') {
				if (in.length > off) { // 是否有下一个字符
					c = in[off++]; // 取出下一个字符
				} else {
					out[outLen++] = '\\'; // 末字符为'\'，返回
					break;
				}
				if (c == 'u') { // 如果是"\\u"
					int value = 0;
					if (in.length > off + 4) { // 判断"\\u"后边是否有四个字符
						boolean isUnicode = true;
						for (int i = 0; i < 4; i++) { // 遍历四个字符
							c = in[off++];
							switch (c) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + c - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + c - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + c - 'A';
								break;
							default:
								isUnicode = false; // 判断是否为unicode码
							}
						}
						if (isUnicode) { // 是unicode码转换为字符
							out[outLen++] = (char) value;
						} else { // 不是unicode码把"\\uXXXX"填入返回值
							off = off - 4;
							out[outLen++] = '\\';
							out[outLen++] = 'u';
							out[outLen++] = in[off++];
						}
					} else { // 不够四个字符则把"\\u"放入返回结果并继续
						out[outLen++] = '\\';
						out[outLen++] = 'u';
						continue;
					}
				} else {
					switch (c) { // 判断"\\"后边是否接特殊字符，回车，tab一类的
					case 't':
						c = '\t';
						out[outLen++] = c;
						break;
					case 'r':
						c = '\r';
						out[outLen++] = c;
						break;
					case 'n':
						c = '\n';
						out[outLen++] = c;
						break;
					case 'f':
						c = '\f';
						out[outLen++] = c;
						break;
					default:
						out[outLen++] = '\\';
						out[outLen++] = c;
						break;
					}
				}
			} else {
				out[outLen++] = (char) c;
			}
		}
		return new String(out, 0, outLen);
	}
	
}
