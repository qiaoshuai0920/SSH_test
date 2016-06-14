package com.qs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	public static void main(String[] args) {
		getConnection();
	}
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.0.103:3306/nmgrenzheng?user=root&password=123456&useUnicode=true&characterEncoding=UTF8");
			//Connection conn = DriverManager.getConnection("jdbc:mysql://rdso941i305k01fi43g5.mysql.rds.aliyuncs.com:3306/nxrenzheng?user=nxrenzheng&password=nxrenzheng&useUnicode=true&characterEncoding=UTF8");
			 return conn;
		} catch (Exception e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement prep,
			ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prep != null)
					prep.close();
			} catch (Exception e) {
				e.printStackTrace();

			}finally{
				try{
					if(con!=null)
						con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
