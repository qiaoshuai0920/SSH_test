package com.qs.util;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

public class OneBarcodeUtil {
	public static void main(String[] paramArrayOfString) {  
	    try {  
	      JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(),WidthCodedPainter.getInstance(),BaseLineTextPainter.getInstance());  
	      //xx  
	      String str = "076001";
	      BufferedImage localBufferedImage = localJBarcode.createBarcode(str);  
	      saveToPNG(localBufferedImage, "ff.png");
	  
	     } catch (Exception localException) {
	       localException.printStackTrace();
	     }
	 }
	
	/**
	 * 生成一维码图片
	 * @param num 流水码
	 * @param realPath 生成图片路径
	 */
	public static void getOneBarcode(String num,String realPath) {
	
		try {
		    JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(),WidthCodedPainter.getInstance(),BaseLineTextPainter.getInstance());  
		  
		    BufferedImage localBufferedImage = localJBarcode.createBarcode(num);  
		    saveToPNG(localBufferedImage, realPath);
		  
		} catch (Exception localException) {
		      localException.printStackTrace();
		}
	}
	  
	  static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {  
	     saveToFile(paramBufferedImage, paramString, "jpg");  
	  }
	  
	  static void saveToPNG(BufferedImage paramBufferedImage, String paramString) {
	     saveToFile(paramBufferedImage, paramString, "png");
	  }

	  static void saveToGIF(BufferedImage paramBufferedImage, String paramString) {
	     saveToFile(paramBufferedImage, paramString, "gif");
	  }
	  
	  static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2) {
	     try {
	        FileOutputStream localFileOutputStream = new FileOutputStream(paramString1);
	        ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96); 
	        localFileOutputStream.close();
	     } catch (Exception localException) {
	        localException.printStackTrace();
	     }
	  }
}
