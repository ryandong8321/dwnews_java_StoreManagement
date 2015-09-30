package com.dwnews.storemanagement.service.item;

import java.util.Map;

import com.dwnews.storemanagement.entity.Items;
import com.dwnews.storemanagement.service.IStoreManagementBaseService;

public interface IItemManagementService extends IStoreManagementBaseService<Items, Integer>{
	
	public Map<String,Object> findItems(Integer rowNum, Integer pageSize, Map<String, Object> parameters, Map<String, String> sort);
	
	public boolean deleteItems(String itemIds);

}
