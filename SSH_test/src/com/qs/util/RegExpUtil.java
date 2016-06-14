package com.qs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {
	
	public static final String INTEGER="^(\\+|\\-)?\\d+$";	//整数
    public static final String DOUBLE="^(\\+|\\-)?\\d+(\\.\\d+)?$"; //小数
    public static final String IDCARD="^\\d{15}|\\d{18}|^\\d{17}(\\d|X|x)$";	//身份证
    public static final String PHONE="(^0\\d{2,3}\\-?\\d{7,8}$)|(^((\\+86)|(86))?1[3|4|5|6|7|8][0-9]{9}$)|(^4\\d{2}\\-?\\d{3}\\-?\\d{4}$)";   //电话号码
    public static final String CONTACT="^\\d{0,}\\-?\\d{0,}\\-?\\d{0,}$";   //电话号码
    public static final String DATE="^(\\d{1,4})(-|\\/|\\.)?([0]?[1-9]|1[0-2])(-|\\/|\\.)?([0]?[1-9]|[1-3][0-9])$"; //日期   2013-10-10 2013/10/10 2013.10.10  20131010 201355
    public static final String TIME="^((2[0-3])|([0-1]?[0-9]))[:]([0-5]?[0-9])$"; 	//时间  20:25
    public static final String EMAIL="^\\w+@\\w+\\.\\w+$"; 	//邮箱 
    
    /**
	 * 正则验证
	 * @param regExp 正则表达式
	 * @param str 验证的字符串
	 * @author Learrings
	 * @createTime 2013.11.13
	 */
    public static boolean verification(String regExp,String str) {
    	Pattern p = Pattern.compile(regExp);
    	Matcher m = p.matcher(str);
    	return m.matches();
    }
    
    public static void main(String[] args) {
    	System.out.println(verification(INTEGER,"011111111"));
    	System.out.println(verification(IDCARD,"370681198012065"));
    	System.out.println(verification(EMAIL,"aa@aa..aa"));
	}
}
