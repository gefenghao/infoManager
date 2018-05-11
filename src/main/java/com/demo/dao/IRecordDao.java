package com.demo.dao;

import java.util.List;
import java.util.Map;

import com.demo.entity.IRecord;

public interface IRecordDao {  
    
    //插入数据
    public int insert(IRecord iRecord); 
    //删除数据
    public int delete(IRecord iRecord);
    //查询数据是否存在
    public boolean isExsitsRecord(IRecord record);
    
    public List<IRecord> queryIRcordList(Map<String,String> queryParams,int page,int pageSize);
    
    public int queryToal(Map<String,String> queryParams);
    
}  