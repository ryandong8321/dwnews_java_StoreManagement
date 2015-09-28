package com.dwnews.storemanagement.service.brand;

import java.util.List;
import java.util.Map;

import com.dwnews.storemanagement.entity.Brand;
import com.dwnews.storemanagement.service.IStoreManagementBaseService;

public interface IBrandManagementService extends IStoreManagementBaseService<Brand, Integer>{
	
	public List<Map<String,Object>> findBrands(Map<String, Object> parameters);
	
	public Map<String,Object> findBrands(Integer rowNum, Integer pageSize, Map<String, Object> parameters);
	
	public boolean deleteBrands(String brandIds);

}
