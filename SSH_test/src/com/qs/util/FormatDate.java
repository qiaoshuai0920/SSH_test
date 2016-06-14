package com.qs.util;

public class FormatDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String asda =format2("11");
		System.out.println(asda);
		
	}
	
	public static String format(String str){
		String datestr="";
		for(int i=0;i<str.length();i++){
			String str2=str.substring(i,i+1);
			//System.out.println(str2);
			int k=Integer.parseInt(str2);
			//System.out.println(k);
			String str3=format2(k);
			datestr=datestr+str3;
		}
		
		
		return datestr;
	}
	
	public static String format2(String str){
		String datestr="";
		
			if(str.substring(0,1).equals("0")){
				for(int i=0;i<str.length();i++){
				
				String str2=str.substring(i,i+1);
				//System.out.println(str2);
				int k=Integer.parseInt(str2);
				//System.out.println(k);
				String str3=format3(k);
				datestr=datestr+str3;}
			}else if(str.substring(0,1).equals("1")) {
				for(int i=0;i<str.length();i++){
				String str2=str.substring(i,i+1);
				//System.out.println(str2);
				int k=Integer.parseInt(str2);
				//System.out.println(k);
				String str3=format4(k);
				datestr=datestr+str3;}
				
			}else {
				
				int k=Integer.parseInt(str.substring(0,1));
				String str4=format3(k);
				int k2=Integer.parseInt(str.substring(1,2));
				String str42=format3(k2);
				datestr=str4+"十"+str42;
				
			}
			
		
		
		if(datestr.equals("十十")){
			datestr="十一";
		}
		return datestr;
	}
	
	
	public static String  format2(int k){
		
		switch (k) {
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "六";
		case 7:
			return "七";
		case 8:
			return "八";
		case 9:
			return "九";
		case 0:
			return "〇";

		default:
			return "〇";
		}
		
		
	}
	
public static String  format3(int k){
		
		switch (k) {
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "六";
		case 7:
			return "七";
		case 8:
			return "八";
		case 9:
			return "九";
		case 0:
			return "";

		default:
			return "";
		}
		
		
	}

public static String  format4(int k){
	
	switch (k) {
	case 1:
		return "十";
	case 2:
		return "二";
	case 3:
		return "三";
	case 4:
		return "四";
	case 5:
		return "五";
	case 6:
		return "六";
	case 7:
		return "七";
	case 8:
		return "八";
	case 9:
		return "九";
	case 0:
		return "";

	default:
		return "";
	}
	
	
}

}
