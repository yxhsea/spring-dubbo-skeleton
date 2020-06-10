package com.skeleton.foundation.utils.date;

import com.skeleton.foundation.utils.date.type.TimeType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateRelatedUtils {
	public final static String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public final static String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String SHORT_DATE_FORMAT = "yyyy-MM-dd";
	public final static String UNSIGNED_DATE_FORMAT = "yyyyMMddHHmmss";
	public final static String[] longDateFormatArray = new String[] { LONG_DATE_FORMAT };
	public final static String[] shortDateFormatArray = new String[] { SHORT_DATE_FORMAT };
	public final static String[] unsignedDateFormatArray = new String[] { UNSIGNED_DATE_FORMAT };
	public final static String[] dateFormatArray = new String[] { LONG_DATE_FORMAT, SHORT_DATE_FORMAT, UNSIGNED_DATE_FORMAT };

	/**
	 * 获取当前时间
	 */
	public static long getCurrentMilliseconds() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取时间
	 */
	public static Date convertMillisecondToDate(long millisecond) {
		return new Date(millisecond);
	}

	/**
	 * 格式化：yyyy-MM-dd HH:mm:ss
	 */
	public static String convertMillisecondToLongDateString(long millisecond) {
		return DateFormatUtils.format(millisecond, LONG_DATE_FORMAT);
	}

	/**
	 * 格式化：yyyy-MM-dd
	 */
	public static String convertMillisecondToShortDateString(long millisecond) {
		return DateFormatUtils.format(millisecond, SHORT_DATE_FORMAT);
	}

	/**
	 * 格式化：yyyyMMddHHmmss
	 */
	public static String convertMillisecondToUnsignedDateString(long millisecond) {
		return DateFormatUtils.format(millisecond, UNSIGNED_DATE_FORMAT);
	}

	/**
	 * 格式化：yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static String convertMillisecondToFullDateString(long millisecond) {
		return DateFormatUtils.format(millisecond, FULL_DATE_FORMAT);
	}

	/**
	 * 格式化：yyyy-MM-dd HH:mm:ss
	 */
	public static String convertStringToLongDateString(String dateString) {
		long ret = 0;
		if (!StringUtils.isBlank(dateString)) {
			try {
				Date date = DateUtils.parseDateStrictly(dateString, dateFormatArray);
				ret = date.getTime();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return convertMillisecondToLongDateString(ret);
	}

	/**
	 * 格式化：yyyy-MM-dd
	 */
	public static String convertStringToShortDateString(String dateString) {
		long ret = 0;
		if (!StringUtils.isBlank(dateString)) {
			try {
				Date date = DateUtils.parseDateStrictly(dateString, dateFormatArray);
				ret = date.getTime();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return convertMillisecondToShortDateString(ret);
	}

	/**
	 * 格式化：yyyyMMddHHmmss
	 */
	public static String convertStringToUnsignedDateString(String dateString) {
		long ret = 0;
		if (!StringUtils.isBlank(dateString)) {
			try {
				Date date = DateUtils.parseDateStrictly(dateString, dateFormatArray);
				ret = date.getTime();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return convertMillisecondToUnsignedDateString(ret);
	}

	/**
	 * 格式化：yyyy-MM-dd HH:mm:ss
	 */
	public static String convertToLongDateString(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, LONG_DATE_FORMAT);
	}

	/**
	 * 格式化：yyyy-MM-dd
	 */
	public static String convertToShortDateString(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, SHORT_DATE_FORMAT);
	}

	/**
	 * 格式化：yyyyMMddHHmmss
	 */
	public static String convertToUnsignedDateString(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, UNSIGNED_DATE_FORMAT);
	}

	/**
	 * 格式化
	 */
	public static String convertMillisecondToDateString(long millisecond, String pattern) {
		return DateFormatUtils.format(millisecond, pattern);
	}

	/**
	 * 获取时间 格式：yyyy-MM-dd HH:mm:ss ,yyyy-MM-dd ,yyyyMMddHHmmss
	 */
	public static long convertDateStringToMillisecond(String dateString) {
		long ret = 0;
		if (!StringUtils.isBlank(dateString)) {
			try {
				Date date = DateUtils.parseDateStrictly(dateString, dateFormatArray);
				ret = date.getTime();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return ret;
	}

	/**
	 * 获取时间 格式：yyyy-MM-dd HH:mm:ss ,yyyy-MM-dd ,yyyyMMddHHmmss
	 */
	public static Date convertDateString(String dateString) {
		if (!StringUtils.isBlank(dateString)) {
			try {
				return DateUtils.parseDateStrictly(dateString, dateFormatArray);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/**
	 * 获取时间 格式：yyyy-MM-dd HH:mm:ss
	 */
	public static Date convertLongDateString(String dateString) {
		if (!StringUtils.isBlank(dateString)) {
			try {
				return DateUtils.parseDateStrictly(dateString, longDateFormatArray);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获取时间 格式：yyyy-MM-dd
	 */
	public static Date convertShortDateString(String dateString) {
		if (!StringUtils.isBlank(dateString)) {
			try {
				return DateUtils.parseDateStrictly(dateString, shortDateFormatArray);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获取时间 格式：yyyyMMddHHmmss
	 */
	public static Date convertUnsignedDateString(String dateString) {
		if (!StringUtils.isBlank(dateString)) {
			try {
				return DateUtils.parseDateStrictly(dateString, unsignedDateFormatArray);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/**
	 * 获取时间 格式：yyyy-MM-dd HH:mm:ss
	 */
	public static long convertLongDateStringToMillisecond(String dateString) {
		long ret = 0;
		if (!StringUtils.isBlank(dateString)) {
			try {
				Date date = DateUtils.parseDateStrictly(dateString, longDateFormatArray);
				ret = date.getTime();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return ret;
	}

	/**
	 * 获取时间 格式：yyyy-MM-dd
	 */
	public static long convertShortDateStringToMillisecond(String dateString) {
		long ret = 0;
		if (!StringUtils.isBlank(dateString)) {
			try {
				Date date = DateUtils.parseDateStrictly(dateString, shortDateFormatArray);
				ret = date.getTime();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return ret;
	}

	/**
	 * 获取时间 格式：yyyyMMddHHmmss
	 */
	public static long convertUnsignedDateStringToMillisecond(String dateString) {
		long ret = 0;
		if (!StringUtils.isBlank(dateString)) {
			try {
				Date date = DateUtils.parseDateStrictly(dateString, unsignedDateFormatArray);
				ret = date.getTime();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return ret;
	}

	/**
	 * 获取时间
	 */
	public static long convertDateStringToMillisecond(String dateString, String[] dateFormats) {
		long ret = 0;
		if (!StringUtils.isBlank(dateString)) {
			try {
				Date date = DateUtils.parseDateStrictly(dateString, dateFormats);
				ret = date.getTime();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return ret;
	}

	/**
	 * 获取当前时间 格式化：yyyy-MM-dd
	 */
	public static String getCurrentShortDateString() {
		return convertMillisecondToShortDateString(getCurrentMilliseconds());
	}

	/**
	 * 获取当前时间 格式化：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentLongDateString() {
		return convertMillisecondToLongDateString(getCurrentMilliseconds());
	}

	/**
	 * 获取当前时间 格式化：yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static String getCurrentFullDateString() {
		return convertMillisecondToFullDateString(getCurrentMilliseconds());
	}

	/**
	 * 获取当前时间 格式化：yyyyMMddHHmmss
	 */
	public static String getCurrentUnsignedDateString() {
		return convertMillisecondToUnsignedDateString(getCurrentMilliseconds());
	}

	/**
	 * 校验日期格式：yyyy-MM-dd HH:mm:ss
	 */
	public static boolean validateLongDateFormat(String dateString) {
		return DateRelatedUtils.validateDateFormat(dateString, new String[] { LONG_DATE_FORMAT });
	}

	/**
	 * 校验日期格式：yyyy-MM-dd
	 */
	public static boolean validateShortDateFormat(String dateString) {
		return DateRelatedUtils.validateDateFormat(dateString, new String[] { SHORT_DATE_FORMAT });
	}

	/**
	 * 校验日期格式：yyyyMMddHHmmss
	 */
	public static boolean validateUnsignedDateFormat(String dateString) {
		return DateRelatedUtils.validateDateFormat(dateString, new String[] { UNSIGNED_DATE_FORMAT });
	}

	/**
	 * 校验日期格式
	 */
	public static boolean validateDateFormat(String dateString, String dateFormat) {
		return DateRelatedUtils.validateDateFormat(dateString, new String[] { dateFormat });
	}

	/**
	 * 校验日期格式
	 */
	public static boolean validateDateFormat(String dateString, String[] dateFormats) {
		try {
			DateUtils.parseDateStrictly(dateString, dateFormats);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 获取月份的起始时间
	 *
	 * @param offset
	 *            相对当前月份偏移量,在当前月份上增加或减少offset个月,年会随着增加或减少的月份变化
	 * @return 月份的起始时间
	 */
	public static long getMillisecondsForMonthBegin(int offset) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MONTH, offset);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取月份的起始时间
	 * 
	 * @param millisecond
	 *            当前时间
	 * @param offset
	 *            相对当前月份偏移量,在当前月份上增加或减少offset个月,年会随着增加或减少的月份变化
	 * @return 月份的起始时间
	 */
	public static long getMillisecondsForMonthBegin(long millisecond, int offset) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MONTH, offset);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取年份的起始时间
	 *
	 * @param offset
	 *            相对当前年份偏移量,在当前年份上增加或减少offset年
	 * @return 年份的起始时间
	 */
	public static long getMillisecondsForYearBegin(int offset) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.YEAR, offset);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取年份的起始时间
	 * 
	 * @param millisecond
	 *            当前时间
	 * @param offset
	 *            相对当前年份偏移量,在当前年份上增加或减少offset年
	 * @return 年份的起始时间
	 */
	public static long getMillisecondsForYearBegin(long millisecond, int offset) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.YEAR, offset);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取起始时间
	 *
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @param day
	 *            日
	 * @return 指定日期的起始时间
	 */
	public static long getMillisecondsBegin(int year, int month, int day) {
		Calendar calendar = GregorianCalendar.getInstance();
		if (year != 0) {
			calendar.set(Calendar.YEAR, year);
		}
		if (month != 0) {
			calendar.set(Calendar.MONTH, month - 1);
		}
		if (day != 0) {
			calendar.set(Calendar.DAY_OF_MONTH, day);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取日期开始时间
	 *
	 * @param millisecond
	 * @return 指定日期的起始时间
	 */
	public static long getMillisecondsBegin(long millisecond) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(millisecond);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取日期结束时间
	 *
	 * @param millisecond
	 * @return 指定日期的结束时间
	 */
	public static long getMillisecondsEnd(long millisecond) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(millisecond);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取日偏移时间
	 * 
	 * @param startTime
	 *            初始时间
	 * @param day
	 *            偏移日
	 * @return 偏移后的时间
	 */
	public static long getExcursionMilliseconds(long startTime, int day) {
		return getExcursionMilliseconds(startTime, 0, 0, day);
	}

	/**
	 * 获取偏移时间
	 * 
	 * @param startTime
	 *            初始时间
	 * @param year
	 *            偏移年份
	 * @param month
	 *            偏移月份
	 * @param day
	 *            偏移日
	 * @return 偏移后的时间
	 */
	public static long getExcursionMilliseconds(long startTime, int year, int month, int day) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(startTime);
		if (year != 0) {
			calendar.add(Calendar.YEAR, year);
		}
		if (month != 0) {
			calendar.add(Calendar.MONTH, month);
		}
		if (day != 0) {
			calendar.add(Calendar.DAY_OF_MONTH, day);
		}
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取偏移时间
	 * 
	 * @param startTime
	 *            初始时间
	 * @param hour
	 *            偏移小时
	 * @param minute
	 *            偏移分钟
	 * @return 偏移后的时间
	 */
	public static long getExcursionMillisecondsForTime(long startTime, int hour, int minute, int second) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(startTime);
		if (hour != 0) {
			calendar.add(Calendar.HOUR_OF_DAY, hour);
		}
		if (minute != 0) {
			calendar.add(Calendar.MINUTE, minute);
		}
		if (second != 0) {
			calendar.add(Calendar.SECOND, second);
		}
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取当天的剩余时间
	 */
	public static long getRemainingTimeOfDay() {
		long nowTime = getCurrentMilliseconds();
		long endTime = getMillisecondsEnd(nowTime);
		return endTime - nowTime;
	}

	/**
	 * 格式化有效耗时,转为整数类型
	 */
	public static String formatValidTimes(long validTimes) {
		if (validTimes % 1000 != 0) {
			return String.format("%s毫秒", validTimes);
		} else if (validTimes % 60000 != 0) {
			return String.format("%s秒", (long) Math.floor(validTimes / 1000));
		} else if (validTimes % 3600000 != 0) {
			return String.format("%s分钟", (long) Math.floor(validTimes / 60000));
		} else if (validTimes % 86400000 != 0) {
			return String.format("%s小时", (long) Math.floor(validTimes / 3600000));
		} else {
			return String.format("%s天", (long) Math.floor(validTimes / 86400000));
		}
	}

	/**
	 * 获取前一月最后一秒
	 * 
	 * @return
	 * @date 2018年11月6日
	 * @author 大超哥
	 */
	public static long getLastDaySeconds(long startTime) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(startTime);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTimeInMillis();
	}

	/**
	 * 获取起始时间
	 *
	 * @param millisecond
	 *            当前时间
	 * @param timeType
	 *            获取起始时间类型
	 * @return 指定日期的起始时间
	 */
	public static long getMillisecondsBeginByTimeType(long millisecond, TimeType timeType) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(millisecond);

		switch (timeType) {
		case YEAR:
			calendar.set(Calendar.MONTH, 0);break;
		case MONTH:
			calendar.set(Calendar.DAY_OF_MONTH, 1);break;
		case DAY:
			calendar.set(Calendar.HOUR_OF_DAY, 0);break;
		case HOUR:
			calendar.set(Calendar.MINUTE, 0);break;
		case MINUTER:
			calendar.set(Calendar.SECOND, 0);break;
		case SECOND:
			calendar.set(Calendar.MILLISECOND, 0);break;
		default:
			break;
		}

		return calendar.getTimeInMillis();
	}

	public static String dateToWeek(String datetime) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance(); // 获得一个日历
		Date datet = null;
		try {
			datet = f.parse(datetime);
			cal.setTime(datet);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}
}
