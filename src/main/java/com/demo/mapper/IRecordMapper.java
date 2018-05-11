package com.demo.mapper;  
  
import java.util.List;
import java.util.Map;

import com.demo.entity.IRecord;  
   
  
public interface IRecordMapper {  
      
    public IRecord selectIRcord(IRecord record);
    
    public void addIRecord(IRecord iRecord); 
    
    public List<IRecord> queryIRcordList(Map<String,String> queryParams);
    
    public int queryToal(Map<String,String> queryParams);
}