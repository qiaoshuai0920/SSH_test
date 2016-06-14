package com.qs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import org.apache.struts2.ServletActionContext;

public class CommonUpload {
	
	public static final String UPLOAD_ROOT = "backend/info/upload";
    public static final int BUFFER_SIZE = 1024;
	/**
	 * 
	 * @createDate 2014.1.8
	 * @alterDate 2013.1.8
	 * @param file 文件
	 * @param fileName 文件名
	 * @param saveDir 存放路径(相对tomcat工程下的路径)
	 * @throws IOException 
	 **/
	public static String upLoad(File file, String fileName, String saveDir) throws IOException {
        //String ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        String pathdir = "/" + UPLOAD_ROOT + "/" + saveDir; //构建文件保存的目录
        //得到图片保存目录的真实路径
        String realpathdir = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(pathdir);
        File savedir = new File(realpathdir);
        if (!savedir.exists()) {
            savedir.mkdirs(); //如果目录不存在就创建
        }
        //String filename = UUID.randomUUID().toString() + "." + ext; //构建文件名称
        String filename=fileName;
        String path = pathdir + "/" + fileName;
        FileOutputStream fileoutstream = new FileOutputStream(new File(realpathdir, filename));
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[BUFFER_SIZE];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fileoutstream.write(buffer, 0, len);
        }
        fileoutstream.close();
        fis.close();
        
        return path;
	}
	
	public static boolean deleteFile(String imagePath, String saveDir) throws IOException {
        boolean result = false;
        String fileName =  imagePath.substring(imagePath.lastIndexOf("/")+1);  
        if (fileName != null) {
        	String pathdir = "/" + UPLOAD_ROOT + "/" + saveDir ;  //构建文件保存的目录
            String realpathdir = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(pathdir);
     
            File file = new File(realpathdir,fileName);    
            if (file.exists()) {	
                file.delete();
                result = true;
            }
        }
        return result;
    }
	
	/**
	 * 导入excel后 删除tomcat下的excel文件
	 * @param realPath
	 * @return
	 * @throws IOException
	 */
	public static boolean deleteExcel(String realPath) throws IOException {
		boolean result = false;
		String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
		String realpathdir = realPath.substring(0,realPath.lastIndexOf("\\"));
		
        File file = new File(realpathdir,fileName);
        if (file.exists()) {
            file.delete();
            result = true;
        }
       return result;
    }
	
	/**
     * 构建二维码图片
     * @createDate 2014-3-11
     */
    public static String writeTwoCodePath(String pathdir,String filename) {
       
        String realpathdir = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(pathdir);
      
        File savedir = new File(realpathdir);
        if (!savedir.exists()) {
            savedir.mkdirs(); //如果目录不存在就创建
        }

        String path = realpathdir + File.separator + filename;
        File file1 = new File(path);
        try {
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
