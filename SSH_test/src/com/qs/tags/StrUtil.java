package com.qs.tags;
public class StrUtil {
	//判断对象是否为空
	public static boolean isNull(Object AObject) {
		return (AObject == null);
	}
    //判断字符串是否为空
	public static boolean isEmpty(String AStr) {
		if (StrUtil.isNull(AStr)) {
			return true;
		} else {
			return (AStr.equals(""));
		}
	}
	//判断字符串是否为空
	public static boolean isEmpty(String AStr, Boolean ACompriseNull) {
		if (ACompriseNull) {
			return isEmpty(AStr);
		} else {
			return (!StrUtil.isNull(AStr) && AStr.equals(""));
		}
	}
    //判断两个字符串是否相等
	public static boolean isEqual(String AFirstStr, String ASecondStr,
			Boolean AIgnoreCase) {
		if (StrUtil.isNull(AFirstStr)) {
			return StrUtil.isNull(ASecondStr);
		} else if (StrUtil.isNull(ASecondStr)) {
			return false;
		} else if (AIgnoreCase) {
			return AFirstStr.toLowerCase().equals(ASecondStr.toLowerCase());
		} else {
			return AFirstStr.equals(ASecondStr);
		}
	}
	//判断两个字符串是否相等
	public static boolean isEqual(String AFirstStr, String ASecondStr) {
		return isEqual(AFirstStr, ASecondStr, true);
	}
   

}
