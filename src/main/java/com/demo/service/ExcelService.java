package com.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 功能：读取excel
 * @author fenghao.ge
 *
 */
public interface ExcelService{
	/**
	 * 功能：读取excel文件
	 */
	public List<String[]> readExcelData(File file, String filename,int ignoreRows) throws IOException;
}
