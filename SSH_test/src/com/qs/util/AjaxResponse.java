package com.qs.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class AjaxResponse {

    public static void responseOut(Object value){

        HttpServletResponse response = responseCommon();
        try {
            response.getWriter().print(value.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * Ajax输出html
	 **/
	public static void ajaxPrintByJson(Object content) {
		HttpServletResponse response = responseCommon();
		try {	
			response.setContentType("text/json;charset=UTF-8");
			java.io.PrintWriter out = response.getWriter();
			out.print(content);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static void responseListOut(List<Object> list){

        HttpServletResponse response = responseCommon();
        for(int i = 0; i < list.size(); i++){
            Object listValue = list.get(i);
            try {
                response.getWriter().println(listValue.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void responseOutXml(Object value){

        HttpServletResponse response = responseCommonXml();
        try {
            response.getWriter().print(value.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HttpServletResponse responseCommonXml(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        return response;
    }
    
    public static HttpServletResponse responseCommon(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        return response;
    }
    
    public static void responseName(String fileName, HttpServletResponse response) {
    	//对文件名作处理，避免中文乱码问题
		String name;
		try {
			name = new String(fileName.getBytes("utf-8"),"iso8859-1");
			response.setContentType("application/x-msdownload");
	        response.addHeader("Content-Disposition","attachment;filename="+name);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
