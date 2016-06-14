package com.qs.enums;

public class FilterEnums {

    public enum WebType {
    	addUser("/user/",".action"),
    	delUser("/user/",".action"),
    	listUser("/user/",".action"),
    	loginUser("/user/",".action"),
    	jumpLogin("/user/",".action"),
    	index("",".jsp"),
    	alipayapi("",".jsp"),
    	notify_url("",".jsp"),
    	return_url("",".jsp"),
    	//登录
    	login("/backend/",".jsp");
    	
    	
    	private String packageName;
    	private String type;
    	private WebType() {
    	}

    	private WebType(String packageName,String type) {
    		this.packageName = packageName;
    		this.type = type;
    	}
   	   	
    	public static WebType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IndexOutOfBoundsException("Invalid ordinal");
            }
            return values()[ordinal];
        }

		public String getPackageName() {
			return packageName;
		}

		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
    }
}
