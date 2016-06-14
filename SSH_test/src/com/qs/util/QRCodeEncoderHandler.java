package com.qs.util;    
  
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;
      
  
/**  
 * 二维码生成器 
 * @author sxf 
 */     
public class QRCodeEncoderHandler {    
  
    /**   
     * 生成二维码(QRCode)图片  
     * @param content   
     * @param imgPath   
     */ 
    public static void encoderQRCode(String content, String imgPath) {    
  
        try {    
            Qrcode qrcodeHandler = new Qrcode();      
            qrcodeHandler.setQrcodeErrorCorrect('M');      
            qrcodeHandler.setQrcodeEncodeMode('B');     
            qrcodeHandler.setQrcodeVersion(7);    
     
            System.out.println(content);    
            byte[] contentBytes = content.getBytes("utf-8");    
            BufferedImage bufImg = new BufferedImage(140, 140,    
                    BufferedImage.TYPE_INT_RGB);    
  
            Graphics2D gs = bufImg.createGraphics();    
            gs.setBackground(Color.WHITE);     
            gs.clearRect(0, 0, 140, 140);    
  
            // 设定图像颜色> BLACK      
            gs.setColor(Color.BLACK);    
  
            // 设置偏移量，不设置可能导致解析错误
            int pixoff = 2;    
            // 输出内容> 二维码  
            if (contentBytes.length > 0 && contentBytes.length < 120) {    
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);    
                for (int i = 0; i < codeOut.length; i++) {    
                    for (int j = 0; j < codeOut.length; j++) {    
                        if (codeOut[j][i]) {    
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);    
                        }    
                    }    
                }    
            } else {    
                System.err.println("QRCode content bytes length = "    
                		+ contentBytes.length + " not in [ 0,120 ]. ");    
            }    
            gs.dispose();    
            bufImg.flush();    
  
            File imgFile = new File(imgPath);    
            if (!imgFile.exists()) {
            	imgFile.mkdirs(); //如果目录不存在就创建
            }  
            // 生成二维码QRCode图片     
            ImageIO.write(bufImg, "jpg", imgFile);    

        } catch (Exception e) {      
            e.printStackTrace();    
        }  
    } 
    
    public static int createQRCode(String content,String imgPath,String ccbPath){
    	try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);
			byte[] contentBytes =content.getBytes("gb2312");
			BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 140, 140);
			
			//设定图像颜色 > black
			gs.setColor(Color.BLACK);
			//设置偏移量  不设置可能导致解析出错
			int pixoff =2;
			//输出内容 》二维码
			if (contentBytes.length > 0 && contentBytes.length < 120) {    
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);    
                for (int i = 0; i < codeOut.length; i++) {    
                    for (int j = 0; j < codeOut.length; j++) {    
                        if (codeOut[j][i]) {    
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);    
                        }    
  
                    }    
  
                }    
  
            } else {    
  
                System.err.println("QRCode content bytes length = "    
  
                        + contentBytes.length + " not in [ 0,120 ]. ");    
                return -1;
            }    
			//Image img=ImageIO.read(new File("c://test/100000003.jpg"));//实例化一个Image对象         
			Image img=ImageIO.read(new File(ccbPath));//实例化一个Image对象         
			
			gs.drawImage(img, 50, 50, null);

			gs.dispose();
            bufImg.flush();    
            File imgFile = new File(imgPath);    
  
            if (!imgFile.exists()) {
            	imgFile.mkdirs(); //如果目录不存在就创建
            }  
            // 生成二维码QRCode图片     
            ImageIO.write(bufImg, "png", imgFile);    
			
 		} catch (Exception e) {
			e.printStackTrace();
			return -100;
		}
 		return 0;
    }

  
    /**  
     * @param args the command line arguments  
     */     
    public static void main(String[] args) {    
        String imgPath = "c://test/100000001.jpg";    
        String ccbPath = "c://test/1.png";    
  
        String content = "青岛相智信息技术有限公司";    
  
        QRCodeEncoderHandler handler = new QRCodeEncoderHandler();    
  
       //handler.encoderQRCode(content, imgPath); 
        handler.createQRCode(content, imgPath, ccbPath);
        System.out.println("encoder QRcode success");    
    }
}  