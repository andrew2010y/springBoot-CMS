package com.lzkj.lxzb.rest.modular.user.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtils {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 导出Excel
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static void exportExcel(String[] cellTitle, 
			List<Map<String, Object>> datas, String fileName, HttpServletResponse response) throws Exception {

		
		if(fileName == null || "".equals(fileName)){
			throw new Exception("请指定EXCEL工作表名称!");
		}
		
		if(cellTitle == null || cellTitle.length < 1){
			throw new Exception("请为Excel文件指定表头!");
		}
		
		if(datas == null || datas.size() < 1){
		//	throw new Exception("请为Excel文件设置正文!");
		}
		
		OutputStream out = null;
		StringBuffer fileNameBuffer = new StringBuffer();
        fileNameBuffer.append(fileName);
        SimpleDateFormat fmtDate = new SimpleDateFormat("yyyyMMddHHmmss");
        fileNameBuffer.append(fmtDate.format(new Date()));
        fileNameBuffer.append(".xls");
		
        try {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("用户提现记录");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
	
			HSSFCell cell = null;
			for (int i = 0; i < cellTitle.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(cellTitle[i]);
				cell.setCellStyle(style);
			}
			
			for (int d = 0; d < datas.size(); d++) {
				row = sheet.createRow(d + 1);
				Map<String, Object> data = datas.get(d);
				int index = 0;
				for (String key : data.keySet()) {
					row.createCell(index++).setCellValue(data.get(key) + "");
				}
				
			}
			
			response.setContentType("application/msexcel");
	        response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileNameBuffer.toString().getBytes("GBK"), "ISO-8859-1"));
	        
			out = response.getOutputStream();
			wb.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 导出Excel 依据key[] 使单元格与表头对应,keyTitle顺序需与cellTitle顺序一致
	 * @author aiwei
	 * @return
	 * @throws Exception 
	 * 
	 */
	public static void exportExcelByKey(String[] cellTitle, String[] keyTitle,
			List<Map<String, Object>> datas, String fileName, HttpServletResponse response) throws Exception {

		
		if(fileName == null || "".equals(fileName)){
			throw new Exception("请指定EXCEL工作表名称!");
		}
		
		if(cellTitle == null || cellTitle.length < 1){
			throw new Exception("请为Excel文件指定表头!");
		}
		
		if(datas == null || datas.size() < 1){
			throw new Exception("请为Excel文件设置正文!");
		}
		
		OutputStream out = null;
		StringBuffer fileNameBuffer = new StringBuffer();
        fileNameBuffer.append(fileName);
        SimpleDateFormat fmtDate = new SimpleDateFormat("yyyyMMddHHmmss");
        fileNameBuffer.append(fmtDate.format(new Date()));
        fileNameBuffer.append(".xls");
		
        try {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("用户提现记录");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
	
			HSSFCell cell = null;
			for (int i = 0; i < cellTitle.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(cellTitle[i]);
				cell.setCellStyle(style);
			}
			
			for (int d = 0; d < datas.size(); d++) {
				row = sheet.createRow(d + 1);
				Map<String, Object> data = datas.get(d);
				
				int index = 0;
				for(String key : keyTitle){
					row.createCell(index++).setCellValue(data.get(key) + "");
				}
								
			}
			
			response.setContentType("application/msexcel");
	        response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileNameBuffer.toString().getBytes("GBK"), "ISO-8859-1"));
	        
			out = response.getOutputStream();
			wb.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
