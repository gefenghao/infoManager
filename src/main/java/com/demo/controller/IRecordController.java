package com.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.demo.entity.IRecord;
import com.demo.service.ExcelService;
import com.demo.service.IRecordServiceImpl;
/*
 * 功能：获取交易记录,查询统计
 * @author fenghao.ge
 *
 */
@Controller
@RequestMapping("/iRecord")
public class IRecordController {
	
    @Autowired
    IRecordServiceImpl iRecordService;
    
    @SuppressWarnings("unused")
    @ResponseBody 
    @RequestMapping(value = "/listIRecord.do",produces = "application/json;charset=UTF-8")
    public Map<String,Object> listIRecord(String name,String addr,String tradeDate,String province,String city,String startDate,String endDate,
    		int page,int rows) {
    	Map<String,String> reqmap=new HashMap<String,String>();
    	if(!StringUtils.isEmpty(name)){
    		reqmap.put("name", name);
    	}
    	if(!StringUtils.isEmpty(startDate)){
    		reqmap.put("startDate", startDate);
    	}
    	if(!StringUtils.isEmpty(endDate)){
    		reqmap.put("endDate", endDate);
    	}
    	if(!StringUtils.isEmpty(province)){
    		reqmap.put("province", province);
    	}
    	if(!StringUtils.isEmpty(city)){
    		reqmap.put("city", city);
    	}
    	if(!StringUtils.isEmpty(addr)){
    		reqmap.put("addr", addr);
    	}
    	Map<String,Object> resultMap=new HashMap<String,Object>();
    	int total=iRecordService.queryTotal(reqmap);
    	List<IRecord> resultList=iRecordService.queryIRcordList(reqmap, page,rows);
    	resultMap.put("total", total);
    	resultMap.put("rows", resultList);
    	return resultMap;
    }
    @SuppressWarnings("unused")
    @ResponseBody 
    @RequestMapping(value = "/queryProvince.do",produces = "application/json;charset=UTF-8")
    public List<String> queryProvince() {
    	List<String> resultList=iRecordService.queryProvince();
    	return resultList;
    }
    @SuppressWarnings("unused")
    @ResponseBody 
    @RequestMapping(value = "/queryCity.do",produces = "application/json;charset=UTF-8")
    public List<String> queryCity(String province) {
    	List<String> resultList=iRecordService.queryCity(province);
    	return resultList;
    }
    
    @SuppressWarnings("unused")
    @ResponseBody 
    @RequestMapping(value = "/queryTotalAmount.do",produces = "application/json;charset=UTF-8")
    public Map<String,Object> queryTotalAmount(String name,String addr,String tradeDate,String province,String city,String startDate,String endDate) {
    	Map<String,String> reqmap=new HashMap<String,String>();
    	
    	if(!StringUtils.isEmpty(name)){
    		reqmap.put("name", name);
    	}
    	if(!StringUtils.isEmpty(startDate)){
    		reqmap.put("startDate", startDate);
    	}
    	if(!StringUtils.isEmpty(endDate)){
    		reqmap.put("endDate", endDate);
    	}
    	if(!StringUtils.isEmpty(province)){
    		reqmap.put("province", province);
    	}
    	if(!StringUtils.isEmpty(city)){
    		reqmap.put("city", city);
    	}
    	if(!StringUtils.isEmpty(addr)){
    		reqmap.put("addr", addr);
    	}
    	BigDecimal result=iRecordService.queryTotalAmount(reqmap);
    	Map<String,Object> res=new HashMap<String,Object>();
    	res.put("total", result);
    	return res;
    }
    
}
