package com.dwnews.storemanagement.service.item.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwnews.storemanagement.dao.item.IItemManagementDAO;
import com.dwnews.storemanagement.entity.Items;
import com.dwnews.storemanagement.service.StoreManagementBaseServiceImpl;
import com.dwnews.storemanagement.service.item.IItemManagementService;

@Service("itemManagementService")
public class ItemManagementServiceImpl extends StoreManagementBaseServiceImpl<Items, Integer, IItemManagementDAO> implements IItemManagementService{

	@Autowired
	private IItemManagementDAO itemManagementDAO;
	
	@Override
	protected IItemManagementDAO getCurrentDAO() {
		return this.itemManagementDAO;
	}

}
