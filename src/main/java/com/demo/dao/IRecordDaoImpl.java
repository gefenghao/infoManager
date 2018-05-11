package com.demo.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.demo.entity.IRecord;
import com.demo.mapper.IRecordMapper;

@Repository("iRecordDao")  
public class IRecordDaoImpl implements IRecordDao {  
  
    @Resource(name="sqlSessionTemplate")  
    SqlSessionTemplate sqlSessionTemplate;  
      
    public IRecordMapper getIRecordMapper(){  
        return sqlSessionTemplate.getMapper(IRecordMapper.class);  
    }  
      

	@Override
	public int insert(IRecord iIRecord) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("addIRcord", iIRecord);
	}


	@Override
	public boolean isExsitsRecord(IRecord record) {
		IRecord result=sqlSessionTemplate.selectOne("selectIRcord", record);
		return (result!=null)?true:false;
	}


	@Override
	public List<IRecord> queryIRcordList(Map<String, String> queryParams,int page,int pageSize) {
		// TODO Auto-generated method stub
		if(page!=0&&pageSize!=0){
			queryParams.put("page", (page-1)+"");
			queryParams.put("rows", pageSize+"");
		}
		return sqlSessionTemplate.selectList("queryIRcordList",queryParams);
	}


	@Override
	public int queryToal(Map<String,String> queryParams) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("queryToal",queryParams);
	}
	
	@Override
	public int delete(IRecord record) {
		 // 删除数据
		 return sqlSessionTemplate.delete("deleteIRecord",record);
	}


	public List<String> queryProvince() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("queryProvince");
	}


	public List<String> queryCity(String province) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("queryCity",province);
	}


	public BigDecimal queryTotalAmount(Map<String, String> reqmap) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("queryTotalAmount",reqmap);
	}
}
  
