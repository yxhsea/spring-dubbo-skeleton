package com.skeleton.foundation.utils.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringRelatedUtils {
	private static final Logger logger = LoggerFactory.getLogger(StringRelatedUtils.class);

	/** 大小写字母，数字，中文字符 */
	public final static String ENCODE_REG = "([a-z]|[A-Z]|[0-9]|[\u4E00-\u9FA5])+";
	public final static Pattern encodePattern = Pattern.compile(ENCODE_REG);

	/**
	 * 首字母大写
	 */
	public static String indexToUpperCase(String str) {
		char[] cs = str.toCharArray();
		if (cs[0] >= 97 && cs[0] <= 122) {
            cs[0] -= 32;
        }
		return String.valueOf(cs);
	}

	/**
	 * 首字母小写
	 */
	public static String indexToLowerCase(String str) {
		char[] cs = str.toCharArray();
		if (cs[0] >= 65 && cs[0] <= 90) {
            cs[0] += 32;
        }
		return String.valueOf(cs);
	}

	/**
	 * 获取字符串字节长度
	 */
	public static int getWordCount(String str) {
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			int ascii = Character.codePointAt(str, i);
			if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
		}
		return length;
	}

	/**
	 * 过滤特殊字符，只留下数字，大小写字母，中文
	 */
	public static String filterSpecialChar(String str) {
		if (str != null && !str.isEmpty()) {
			Matcher m = encodePattern.matcher(str);

			StringBuilder newStr = new StringBuilder();

			while (m.find()) {
				String nomalStr = m.group();
				newStr.append(nomalStr);
			}

			return newStr.toString();
		}

		return str;
	}

	/**
	 * @description: 截取对应字符长度
	 * @param orignal
	 * @param start
	 * @param count
	 * @return
	 * @date 2018年6月8日
	 * @author 大超哥
	 */
	public static String substringByte(String orignal, long start, long count) {
		if (StringUtils.isBlank(orignal) || count <= 0) {
            return orignal;
        }

		if (start < 0) {
            start = 0L;
        }

		StringBuffer buff = new StringBuffer();
		try {
			int countLength = orignal.getBytes("utf-8").length;
			if (start == 0 && count > countLength) {
				return orignal;
			}
			if (start >= countLength) {
                return null;
            }
			int len = 0;
			char c;
			for (int i = 0; i < orignal.toCharArray().length; i++) {
				c = orignal.charAt(i);
				if (start == 0) {
					len += String.valueOf(c).getBytes("utf-8").length;
					if (len > count) {
                        break;
                    }
					buff.append(c);
				} else {
					len += String.valueOf(c).getBytes("utf-8").length;
					if (len >= start && len <= start + count) {
						buff.append(c);
					}
					if (len > start + count) {
                        break;
                    }
				}
			}
		} catch (Exception e) {
			logger.error("字段截取异常,错误信息", e);
		}
		return new String(buff);
	}

}
