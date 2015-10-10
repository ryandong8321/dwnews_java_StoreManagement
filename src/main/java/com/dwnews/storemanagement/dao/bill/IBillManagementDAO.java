package com.dwnews.storemanagement.dao.bill;

import java.util.Map;

import com.dwnews.storemanagement.dao.IStoreManagementBaseDAO;
import com.dwnews.storemanagement.entity.ItemsInputOutput;

public interface IBillManagementDAO extends IStoreManagementBaseDAO<ItemsInputOutput, Integer>{
	
	public Map<String, Object> saveBillVerification(String billIds, String type);
	
	public Map<String, Object> deleteBill(String billIds);
}
