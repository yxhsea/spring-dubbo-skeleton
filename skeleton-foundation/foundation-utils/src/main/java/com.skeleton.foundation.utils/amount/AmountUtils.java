package com.skeleton.foundation.utils.amount;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金钱换算工具,舍入模式统一为{@link RoundingMode.DOWN},数据库标度为以元为单位小数点后四位long型
 */
public class AmountUtils {

	public static final int SYS_AMOUNT_PRECISION = 4;

	public static final int SYS_AMOUNT_SCALE = 2;

	/**
	 * 
	 * @param amount
	 *            金额
	 * @param n
	 *            大于零时，amount 小数点右移n位，小于零时，amount 小数点左移n位
	 */
	public static long convertLong(double amount, int n) {
		BigDecimal bd = new BigDecimal(String.valueOf(amount));
		return bd.movePointRight(n).longValue();
	}

	public static long convertLong(double amount) {
		BigDecimal bd = new BigDecimal(String.valueOf(amount));
		return bd.movePointRight(SYS_AMOUNT_PRECISION).longValue();
	}

	/**
	 * @param amount
	 *            金额
	 * @param n
	 *            小于零时，amount 小数点右移n位，大于零时，amount 小数点左移n位
	 */
	public static double convertDouble(double amount, int n) {
		BigDecimal bd = new BigDecimal(amount);
		return bd.movePointLeft(n).doubleValue();
	}

	public static double convertDouble(double amount) {
		BigDecimal bd = new BigDecimal(amount);
		return bd.movePointLeft(SYS_AMOUNT_PRECISION).doubleValue();
	}

	/**
	 * 
	 * @param amount
	 * @param n
	 *            小于零时，amount 小数点右移n位，大于零时，amount 小数点左移n位
	 * @param scale
	 *            小数点后保留位数
	 */
	public static Double convertDouble(double amount, int n, int scale) {
		BigDecimal bd = new BigDecimal(amount);
		return bd.movePointLeft(n).setScale(scale, RoundingMode.DOWN).doubleValue();
	}

	/**
	 * 
	 * @param amount
	 * @param n
	 *            小于零时，amount 小数点右移n位，大于零时，amount 小数点左移n位
	 * @param scale
	 *            小数点后保留位数
	 */
	public static String convertDoubleString(double amount, int n, int scale) {
		BigDecimal bd = new BigDecimal(amount);
		return bd.movePointLeft(n).setScale(scale, RoundingMode.DOWN).toString();
	}

	public static String convertDoubleString(double amount) {
		BigDecimal bd = new BigDecimal(amount);
		return bd.movePointLeft(SYS_AMOUNT_PRECISION).setScale(SYS_AMOUNT_SCALE, RoundingMode.DOWN).toString();
	}

}
