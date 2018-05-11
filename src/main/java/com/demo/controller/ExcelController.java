package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.service.ExcelService;
import com.demo.service.IRecordServiceImpl;
/*
 * 功能：获取交易记录,查询统计
 * @author fenghao.ge
 *
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {
	
    @Autowired
    IRecordServiceImpl iRecordService;
    
    @Autowired
    ExcelService excelService;
    @ResponseBody 
    @RequestMapping(value = "/readExcel.do",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Map<String,String> readExcelInfo(HttpServletRequest request,@RequestParam("ignoreRows") int ignoreRows,
            @RequestParam("file") MultipartFile file) throws Exception{
    
        Map<String,String>  resultMap=new HashMap<String,String>();
		try {
		  String path = request.getServletContext().getRealPath("/upload");
          //上传文件名
          String filename = file.getOriginalFilename();
          File filepath = new File(path,filename);
         //判断路径是否存在，如果不存在就创建一个
          if (!filepath.getParentFile().exists()) { 
              filepath.getParentFile().mkdirs();
          }
          //将上传文件保存到一个目标文件当中
          file.transferTo(new File(path + File.separator + filename));
		  List<String[]> result=excelService.readExcelData(filepath,filename,ignoreRows);
		  iRecordService.addIRecords(result);
		  resultMap.put("result","01");
		} catch ( IOException e) {
			resultMap.put("result","00");
			e.printStackTrace();
		}
		return resultMap;
		
    }

}
