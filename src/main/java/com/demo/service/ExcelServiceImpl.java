package com.demo.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 功能：读取excel
 * @author lenovo
 *
 */
@Service
public class ExcelServiceImpl implements ExcelService{
	

	@Override
	public List<String[]> readExcelData(File file, String filename,int ignoreRows) throws IOException {
//		File file=new File(filepath);
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(
                file));
        Workbook xssfWorkbook = null; 
        if (filename.indexOf(".xlsx")!=-1) {  
        	 xssfWorkbook = new XSSFWorkbook(is);    
        } else {    
        	xssfWorkbook = new HSSFWorkbook(is); 
        }    
        Sheet xssfSheet = xssfWorkbook.getSheetAt(0);    
        if (xssfSheet == null) {    
            return null;    
        }    
        ArrayList<String[]> list = new ArrayList<String[]>();    
        int lastRowNum = xssfSheet.getLastRowNum();    
        for (int rowNum = ignoreRows; rowNum <= lastRowNum; rowNum++) {    
            if (xssfSheet.getRow(rowNum) != null) {    
                Row xssfRow = xssfSheet.getRow(rowNum);    
                short firstCellNum = xssfRow.getFirstCellNum();    
                short lastCellNum = xssfRow.getLastCellNum();    
                if (firstCellNum != lastCellNum) {    
                    String[] values = new String[lastCellNum];    
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {    
                        Cell xssfCell = xssfRow.getCell(cellNum);    
                        if (xssfCell == null) {    
                            values[cellNum] = "";    
                        } else {    
                            values[cellNum] = parseExcel(xssfCell);    
                        }    
                    }    
                    list.add(values);    
                }    
            }    
        }    
        return list;    
    }

	private String parseExcel(Cell cell) {  
        String result = new String();  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型  
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
                SimpleDateFormat sdf = null;  
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat  
                        .getBuiltinFormat("h:mm")) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                } else {// 日期  
                    sdf = new SimpleDateFormat("yyyy-MM-dd");  
                }  
                Date date = cell.getDateCellValue();  
                result = sdf.format(date);  
            } else if (cell.getCellStyle().getDataFormat() == 58) {  
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                double value = cell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil  
                        .getJavaDate(value);  
                result = sdf.format(date);  
            } else {  
                double value = cell.getNumericCellValue();  
                CellStyle style = cell.getCellStyle();  
                DecimalFormat format = new DecimalFormat();  
                String temp = style.getDataFormatString();  
                // 单元格设置成常规  
                if (temp.equals("General")) {  
                    format.applyPattern("0.00");  
                }  
                result = format.format(value);  
            }  
            break;  
        case HSSFCell.CELL_TYPE_STRING:// String类型  
            result = cell.getRichStringCellValue().toString();  
            break;  
        case HSSFCell.CELL_TYPE_BLANK:  
            result = "";  
        default:  
            result = "";  
            break;  
        }  
        return result;  

	}
	
}
