package com.qs.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import com.opensymphony.xwork2.ActionContext;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 验证码
 */
public class Captcha extends HttpServlet {
	
	private static final long serialVersionUID = 2L;
	javax.servlet.http.HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
	javax.servlet.http.HttpServletResponse response = org.apache.struts2.ServletActionContext.getResponse();
	public static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
			'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };   //设置随即数字，1和I、0和O容易混淆。

	public static Random random = new Random();

	public static String getRandomString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {                                       //从字符数组中随即选取6个
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

//	public static Color getRandomdColor() {                					//随即选取背景色
//		return new Color(random.nextInt(255), random.nextInt(255), random
//				.nextInt(255));
//	}
//
//	public static Color getReverseColor(Color c) {							//随即选取前景色
//		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c
//				.getBlue());
//	}

	public static Color getReverseColor(int r,int g,int b) {							//随即选取颜色色
		return new Color(r,g,b);
	}
	
	public void getVerifyCode()
			throws ServletException, IOException {
		response.setContentType("image/jpg");								//设置输出类型
		String randomString = getRandomString();							//获取静态方法中的4个字符
//		request.getSession(true).setAttribute("randomString", randomString);//放到session中
		ActionContext.getContext().getSession().put("random", randomString);																	
		int width = 65;													
		int height = 25;

		Color color = getReverseColor(130,139,149);									//获取静态方法中背景色
		Color reverse = getReverseColor(255,255,255);									//获取静态方法中的前景色

		BufferedImage bi = new BufferedImage(width, height,					//创建一个色彩图片
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();									//获取绘图对象
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));				//设置字体
		g.setColor(color);													//设置颜色
		g.fillRect(0, 0, width, height);									//设置背景样式
		g.setColor(reverse);												//设置颜色
		g.drawString(randomString, 11, 20);									//绘制随即字符 ；18、20代表字符的起点位置
//		for (int i = 0, n = random.nextInt(40); i < n; i++) {				//绘制噪点，最多100个
//			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);//绘制单个噪点的（x轴，y轴，噪点的宽度，噪点的高度）
//		}
		ServletOutputStream out = response.getOutputStream();				//转换成JPEG
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);		//编码器
		encoder.encode(bi);													//对图片进行编码
		out.flush();														//输出到客户端
		out.close();
	}

	/**
	 *  ajax 验证码
	 * @date 2013.5.8
	 * 
	 **/
	public void test() {		
		try {
			String arandom=(String)(ActionContext.getContext().getSession().get("random")); 
			response.setContentType("text/html;charset=UTF-8");
			java.io.PrintWriter out = response.getWriter();
			if(arandom==null){	
				out.print("false1");		
			}else if(arandom.equalsIgnoreCase(request.getParameter("captcha_Input"))){
				out.print("true");				
			}else{
				out.print("false2");				
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ServletException, IOException {
		Captcha asd=new Captcha();
		asd.getVerifyCode();
	}

}

