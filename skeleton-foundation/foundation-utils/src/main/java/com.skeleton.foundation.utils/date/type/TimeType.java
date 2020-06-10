package com.skeleton.foundation.utils.date.type;

/**
 * 时间类型
 */
public enum TimeType {
	MILLISECOND(0, "毫秒"),
	SECOND(1, "秒"),
	MINUTER(2, "分"),
	HOUR(3, "时"),
	DAY(4, "天"),
	MONTH(5, "月"),
	YEAR(6, "年");

	private int code;
	private String desc;

	private TimeType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}

	public static TimeType getByCode(int code) {
		for (TimeType type : TimeType.values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}
}