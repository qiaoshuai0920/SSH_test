package com.qs.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
     public static Date getDateFromString(String str){
    	 if(str==null) return new Date();
    	 DateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd");
    	 try {
			return dateForm.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
     }
     public static String getStringFromDate(Date d){
    	 if(d==null) d = new Date();
    	 DateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd");
    	 return dateForm.format(d);
     }
     
     public static String getStrFromDate(Date d){
    	 if(d==null) d = new Date();
    	 DateFormat dateForm = new SimpleDateFormat("yyyy年MM月dd日");
    	 return dateForm.format(d);
     }
}

