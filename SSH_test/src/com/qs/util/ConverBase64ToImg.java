package com.qs.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ConverBase64ToImg {
	
	public static void main(String[] args) {
		// 测试从Base64编码转换为图片文件

		String strImg = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgF" +
				"BQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2w" +
				"BDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQU" +
				"FBQUFBQUFBQUFBQUFBQUFBT/wAARCAB+AGYDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQE" +
				"AAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE" +
				"1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUp" +
				"TVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKzt" +
				"LW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEB" +
				"AQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx" +
				"BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHS" +
				"ElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKm" +
				"qsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBA" +
				"AIRAxEAPwD9UKKKKACiivmn9rX9t7wv+zNZjTEhOv8AjG6iZrfTIJABDwdskzfwqSMdCT2B" +
				"wcAH0B4n8WaL4K0a41bX9VtNH0y3QyS3d7MsUaKOpJJ7V8xeOf8AgqB8BvBs9/aw+IbvXru2X5" +
				"RpdlJLDM2MgJLgKfrnHb1r8oPi18V/iV+0DrT6j4x1u61UeYXhs95W1t+uBHEOBgMRuOWI6k1w" +
				"beCNRBwLWRj7ISKlSjfUxn7XmtBH6zaP/wAFd/g9e3sEd3Z+IrGB2xJPJYKyIMHn5XLHsMAd+or2/wCFv" +
				"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";
		
		GenerateImage(strImg, "D:\\wangyc.jpg");

		// 测试从图片文件转换为Base64编码
		System.out.println(GetImageStr("d:\\wangyc.jpg"));


		}

		public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;

		// 读取图片字节数组
		try {
		InputStream in = new FileInputStream(imgFilePath);
		data = new byte[in.available()];
		in.read(data);
		in.close();
		} catch (IOException e) {
		e.printStackTrace();
		}

		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
		}

		public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
		return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
		// Base64解码
		byte[] bytes = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < bytes.length; ++i) {
		if (bytes[i] < 0) {// 调整异常数据
		bytes[i] += 256;
		}
		}
		// 生成jpeg图片
		OutputStream out = new FileOutputStream(imgFilePath);
		out.write(bytes);
		out.flush();
		out.close();
		return true;
		} catch (Exception e) {
		return false;
		}
		}
}
