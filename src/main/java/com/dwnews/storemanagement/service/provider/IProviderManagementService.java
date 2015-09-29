package com.dwnews.storemanagement.service.provider;

import java.util.List;
import java.util.Map;

import com.dwnews.storemanagement.entity.Providers;
import com.dwnews.storemanagement.service.IStoreManagementBaseService;

public interface IProviderManagementService extends IStoreManagementBaseService<Providers, Integer>{
	
	public List<Map<String,Object>> findProviders(Map<String, Object> parameters);
	
	public Map<String,Object> findProviders(Integer rowNum, Integer pageSize, Map<String, Object> parameters);
	
	public boolean deleteProviders(String providerIds);
	
}
