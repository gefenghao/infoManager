package com.demo.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.IRecordDaoImpl;
import com.demo.entity.IRecord;
/**
 * 功能：记录service
 * @author fenghao.ge
 * @version 1.0
 *
 */
@Service
public class IRecordServiceImpl implements IRecordService {  
  
    @Resource(name="iRecordDao")  
    IRecordDaoImpl iRecordDao; 
    
    @Override
    @Transactional
    public  IRecord addIRecords(List<String[]> list) {   
    	IRecord record=new IRecord();
        for(int i=0;i<list.size();i++){
        	String[] arr=list.get(i);
        	record.setAmount(BigDecimal.valueOf(Double.parseDouble(arr[0])));
        	record.setName(arr[1]);
        	record.setAddr(arr[2]);
        	String areaCode;
        	String[] addrArr=arr[2].split(" ");
            String province=addrArr[0];
            String city=addrArr[1];
            //做特殊处理
            if(province.indexOf("北京")!=-1||province.indexOf("天津")!=-1||province.indexOf("上海")!=-1||province.indexOf("重庆")!=-1){
            	province=addrArr[1];
            	city=addrArr[2];
            }
            record.setProvince(province);
            record.setCity(city);
            record.setTradeDate(arr[3]);
			
        	//如果不存在，直接做插入
        	if(!isExsits(record)){
        		iRecordDao.insert(record);
        	}else{
        		//如果存在，直接删除,刷单数据
        		iRecordDao.delete(record);
        	}
        }
        return null;
    }
    public boolean isExsits(IRecord record){
    	return iRecordDao.isExsitsRecord(record);
    }
    //查询列表
	public List<IRecord> queryIRcordList(Map<String, String> queryParams, int page,int pageSize) {
		
		return iRecordDao.queryIRcordList(queryParams,page,pageSize);
	}
	public int queryTotal(Map<String, String> queryParmas) {
		// TODO Auto-generated method stub
		return iRecordDao.queryToal(queryParmas);
	}
	public List<String> queryProvince() {
		// TODO Auto-generated method stub
		return iRecordDao.queryProvince();
	}
	public List<String> queryCity(String province) {
		// TODO Auto-generated method stub
		return iRecordDao.queryCity(province);
	}
	public BigDecimal queryTotalAmount(Map<String, String> reqmap) {
		// TODO Auto-generated method stub
		 return iRecordDao.queryTotalAmount(reqmap);
	}
	
}  