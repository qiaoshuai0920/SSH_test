package com.qs.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qs.bean.ExcelColumnBean;

public class ReadExcel {   
    /**
	 * 读取绝对位置的文件，生成原生exl对象存在List里
	 * @createTime 2013.12.24
	 * @param excelPath 绝对路径（文件在电脑中的位置）
	 */
    public static List<ExcelColumnBean> readExcel2003(String excelPath){
    	List<ExcelColumnBean> excelColumnList = new ArrayList<ExcelColumnBean>();
		try {
			if(excelPath!=null&&!"".equals(excelPath)){
				InputStream input = new FileInputStream(excelPath);
				POIFSFileSystem fs = new POIFSFileSystem(input);

				HSSFWorkbook wb = new HSSFWorkbook(fs);
				HSSFSheet sheet = wb.getSheetAt(0);//第一张excel表
				if(sheet.getLastRowNum()!=0){
					
				
				Iterator<Row> rows = sheet.rowIterator();
				rows.next();					//过滤第一行
				sheet.getFirstRowNum();
				
					while (rows.hasNext()) {		//单行读取
						ExcelColumnBean excelColumnBean = new ExcelColumnBean();
						HSSFRow row = (HSSFRow) rows.next();
						if (row.getRowNum() > 0) {	//列数存在性
							Iterator<Cell> cells = row.cellIterator();
							while (cells.hasNext()) {//单列读取
								HSSFCell cell = (HSSFCell) cells.next();
					
								//转换excel中类型为字符串
								String str = null;
							
								switch (cell.getCellType()) {
								
								case HSSFCell.CELL_TYPE_NUMERIC: // 数字
									str = String.valueOf(cell.getNumericCellValue());
									break;
								case HSSFCell.CELL_TYPE_STRING: // 字符串
									str = cell.getStringCellValue();
									break;
								case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
									break;
								case HSSFCell.CELL_TYPE_FORMULA: //公式（显示结果）
									str = String.valueOf(cell.getNumericCellValue());
									break;
								case HSSFCell.CELL_TYPE_BLANK: // 无内容
									str = " ";
									break;
								case HSSFCell.CELL_TYPE_ERROR: // 错误
									break;
								default:
									str = "";
									break;
								}
								//列项(初步对应20个列项,可多加项，对象中现有21列可加项)
								switch (cell.getCellNum()) {
								case 0:
									excelColumnBean.setColumnNo0(str);
									break;
								case 1:
									excelColumnBean.setColumnNo1(str);
									break;
								case 2:
									excelColumnBean.setColumnNo2(str);
									break;
								case 3: 
									excelColumnBean.setColumnNo3(str);
									break;
								case 4: 
									excelColumnBean.setColumnNo4(str);
									break;
								case 5: 
									excelColumnBean.setColumnNo5(str);
									break;
								case 6: 
									excelColumnBean.setColumnNo6(str);
									break;
								case 7: 
									excelColumnBean.setColumnNo7(str);
									break;
								case 8: 
									excelColumnBean.setColumnNo8(str);
									break;
								case 9: 
									excelColumnBean.setColumnNo9(str);
									break;
								case 10: 
									excelColumnBean.setColumnNo10(str);
									break;
								case 11: 
									excelColumnBean.setColumnNo11(str);
									break;
								case 12: 
									excelColumnBean.setColumnNo12(str);
									break;
								case 13: 
									excelColumnBean.setColumnNo13(str);
									break;
									
								case 14: 
									excelColumnBean.setColumnNo14(str);
									break;
									
								case 15: 
									excelColumnBean.setColumnNo15(str);
									break;
								
								default:
									break;
								}
							}
						}
						excelColumnList.add(excelColumnBean);
					}
				}
				
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return excelColumnList;
    }
    
    
    public static List<ExcelColumnBean> readExcel2007(String excelPath){
    	List<ExcelColumnBean> excelColumnList = new ArrayList<ExcelColumnBean>();
		try {
			if(excelPath!=null&&!"".equals(excelPath)){
				InputStream input = new FileInputStream(excelPath);
				
				XSSFWorkbook wb = new XSSFWorkbook(input);
				XSSFSheet sheet = wb.getSheetAt(0);//第一张excel表
				if(sheet.getLastRowNum()!=0){
					
				
				Iterator<Row> rows = sheet.rowIterator();
				rows.next();					//过滤第一行
				sheet.getFirstRowNum();
				
					while (rows.hasNext()) {		//单行读取
						ExcelColumnBean excelColumnBean = new ExcelColumnBean();
						XSSFRow row = (XSSFRow) rows.next();
						if (row.getRowNum() > 0) {	//列数存在性
							Iterator<Cell> cells = row.cellIterator();
							while (cells.hasNext()) {//单列读取
								XSSFCell cell = (XSSFCell) cells.next();
					
								//转换excel中类型为字符串
								String str = null;
							
								switch (cell.getCellType()) {
								
								case XSSFCell.CELL_TYPE_NUMERIC: // 数字
									str = String.valueOf(cell.getNumericCellValue());
									break;
								case XSSFCell.CELL_TYPE_STRING: // 字符串
									str = cell.getStringCellValue();
									break;
								case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
									break;
								case XSSFCell.CELL_TYPE_FORMULA: //公式（显示结果）
									str = String.valueOf(cell.getNumericCellValue());
									break;
								case XSSFCell.CELL_TYPE_BLANK: // 无内容
									str = " ";
									break;
								case XSSFCell.CELL_TYPE_ERROR: // 错误
									break;
								default:
									str = "";
									break;
								}
								//列项(初步对应20个列项,可多加项，对象中现有21列可加项)
								switch (cell.getColumnIndex()) {
								
								case 0:
									excelColumnBean.setColumnNo0(str);
									break;
								case 1:
									excelColumnBean.setColumnNo1(str);
									break;
								case 2:
									excelColumnBean.setColumnNo2(str);
									break;
								case 3: 
									excelColumnBean.setColumnNo3(str);
									break;
								case 4: 
									excelColumnBean.setColumnNo4(str);
									break;
								case 5: 
									excelColumnBean.setColumnNo5(str);
									break;
								case 6: 
									excelColumnBean.setColumnNo6(str);
									break;
								case 7: 
									excelColumnBean.setColumnNo7(str);
									break;
								case 8: 
									excelColumnBean.setColumnNo8(str);
									break;
								case 9: 
									excelColumnBean.setColumnNo9(str);
									break;
								case 10: 
									excelColumnBean.setColumnNo10(str);
									break;
								case 11: 
									excelColumnBean.setColumnNo11(str);
									break;
								case 12: 
									excelColumnBean.setColumnNo12(str);
									break;
								case 13: 
									excelColumnBean.setColumnNo13(str);
									break;
									
								case 14: 
									excelColumnBean.setColumnNo14(str);
									break;
									
								case 15: 
									excelColumnBean.setColumnNo15(str);
									break;
								
								default:
									break;
								}
							}
						}
						excelColumnList.add(excelColumnBean);
					}
				}
				
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return excelColumnList;
    }

}

