package com.dwnews.storemanagement.service.bill;

import java.util.Map;
import com.dwnews.storemanagement.entity.ItemsInputOutput;
import com.dwnews.storemanagement.service.IStoreManagementBaseService;

public interface IStoreBillManagementService extends IStoreManagementBaseService<ItemsInputOutput, Integer>{
	
	public Map<String,Object> findStoreBillInformation(Integer rowNum, Integer pageSize, Map<String, Object> parameters, Map<String, String> sort);
	
	public boolean deleteBill(String billIds);

}
