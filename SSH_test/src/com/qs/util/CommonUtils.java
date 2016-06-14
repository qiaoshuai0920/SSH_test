package com.qs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
	public static final String webroot = "/nmgrenzheng";
	public static final String Correct = "Correct";
	public static final String contactMsg = "列表总存在不正确的联系方式格式!";
	public static final String nameMsg = "excel列表中不能存在空的姓名!";
	public static final String contactBlankMsg = "列表总存在空的联系方式!";
	
	public static final String realNameMsg = "用户名不能为空!";
	public static final String peopleTypeMsg = "人员类型不能为空!";
	public static final String idCardMsg = "身份证号码不能为空!";
	public static final String idCardFormat = "身份证号码格式不正确!";
	public static final String phoneMsg = "电话号码不能为空!";
	public static final String phoneFormat = "电话号码格式不正确!";
	public static final String addressMsg = "地址不能为空!";
	
	/**
	 * 返回格式后的sql语句
	 */
	public static String getValidSql(String strSql) {
		String jpql = null;
		jpql = strSql.replace(" ", "").replaceAll("\\s*", "")
				.replace("'", "''").replace("%", "[%]").replace("_", "[_]")
				.replace("^", "[^]").replace("[", "[[]").replace("]", "[]]");
		return jpql;
	}
	
	/**
	  * 不够补零操作
	  * @param num   数值
	  * @param len 长度（几位）
	  * @return    
	  */
	 public static String fillZero(int num ,int len){
	     String temp=String.valueOf(num);
	     while(temp.length()<len){
	       temp="0"+temp;
	     }
	     return temp;
	}
	
	 /**
	  * 用于代办点或优惠单位的编号
	  * 
	  * @param max 分配的数值
	  * @param areaFrontNum 地区编号前缀
	  * @return
	  */
	public static String autoAdd(String max,String areaFrontNum) {
		String stamp=null;
		if(max==null){
		    stamp = areaFrontNum+"001";
		}else{
		  String sub = max.substring(max.length()-3);
		  int i = Integer.parseInt(sub)+1;
		  String temp = CommonUtils.fillZero(i,3);
		  stamp = areaFrontNum+temp;
		}
		return stamp;
	}
	
	/**
	 * 护照编号 查询出最大编号后自动补充为9为
	 * @param maxNum
	 * @return
	 */
	public static String autoFillZero(Integer maxNum) {
		
		String temp = CommonUtils.fillZero(maxNum,9);
		return temp;
	}
	@SuppressWarnings("static-access")
	public static Date validEndDate(Date dat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dat);
		cal.add(cal.YEAR,1);//用Calendar对年加1,绕开判断闰年
		return cal.getTime();//还原为DATE
	}
	public static Date validDate(Date dat,int k) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dat);
		cal.add(cal.YEAR,k);//用Calendar对年加1,绕开判断闰年
		return cal.getTime();//还原为DATE
	}
	
	public static String validDateFormat(String date,int k) {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
		Date dat=null;
		try {
			dat = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(dat);
		cal.add(cal.YEAR,k);//用Calendar对年加1,绕开判断闰年
		return  simpleDateFormat.format(cal.getTime());//还原为DATE
		
	}
	
	@SuppressWarnings("deprecation")
	public static boolean checkValidDate(String startDateStr ,String endDateStr) {
		boolean flag = false;
		
		Date today = new Date();
		int thisYear = today.getYear()+1900;
	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		try {
			Date beginDate = sdf.parse(thisYear + "年"+startDateStr);
			Date endDate = sdf.parse(thisYear + "年"+endDateStr);
		    Date todayDate = sdf.parse(sdf.format(today));
		  
			if((beginDate.getMonth()+1)>(endDate.getMonth()+1)){// 当开始月份大于结束月份 则结束月份+1年
				endDate = validEndDate(endDate);
			}
			if((beginDate.getMonth()+1)>(todayDate.getMonth()+1)) {
				todayDate = validEndDate(todayDate);
			}
			
			if(beginDate.before(todayDate)&&todayDate.before(endDate)) {
				flag = true;
			} else if(beginDate.getTime()==todayDate.getTime()||todayDate.getTime()==endDate.getTime()) {
				flag = true;
			}
		} catch (ParseException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;

	}
	
	/**
	 * 从字符串中提前数字
	 * @param str
	 * @return
	 */
	public static String getNumbers(String str) {
		
		Pattern pattern = Pattern.compile("\\d+");   
		Matcher matcher = pattern .matcher(str);  
		String numberStr = "";
		while (matcher.find()) {  
			numberStr = numberStr+matcher.group(0)+",";  
	    }  
	    return numberStr.substring(0,str.length()-1);

	}
	/**
	 * 因为日期控件是不包括选择的当天的
	 * @return
	 * @createDate 2013-11-29
	 */
	public static String DateAddOneDay(String endDateStr) {
		 Date endDate = DateConvert.getDateFromString(endDateStr);
	     Calendar calendar = Calendar.getInstance();
	     calendar.setTime(endDate); 
	     calendar.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
	     Date newEndDate = calendar.getTime();
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			//格式化开始日期和结束日期
		 dateFormat.format(newEndDate);
	   
	     return dateFormat.format(newEndDate);
	}
	/**
	  * 把身份证的出生年月日用*号代替
	  * @param ID 真实身份证号码
	  * @return 用*替代后的号码
	  */
	public static String replaceSubString(String ID){
		StringBuffer newID = new StringBuffer().append(ID.substring(0,6));
		for(int i = 6;i < ID.length()-4 ;i++) {
			newID.append("*");
		}
		newID.append(ID.substring(ID.length()-4,ID.length()));
	    return newID.toString();
	}

//	public static void main(String args[]) {
////		Date dat = new Date();
////		Calendar cal = Calendar.getInstance();
////		cal.setTime(dat);
////		cal.add(cal.YEAR,1);//用Calendar对年加1,绕开判断闰年
////		dat = cal.getTime();//还原为DATE
////		
////		System.out.println("明年今天是  " + dat);
//		autoFillZero(0);
//		System.out.println(autoFillZero(2));
//	}
	

//		public static void main(String[] args) throws ParseException {
//			String startDate = "2月15日";
//			String endDate = "2月15日";
//		
//			System.out.println(checkValidDate(startDate,endDate));//true  在非优惠时间内
//		}
	
	public static void main(String[] args) {
		String ID = "1233456";
		
		System.out.println(validDateFormat("2015-11-01",-1));
	}

}
