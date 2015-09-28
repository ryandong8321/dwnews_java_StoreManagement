package com.dwnews.storemanagement.dao.item.impl;

import org.springframework.stereotype.Repository;

import com.dwnews.storemanagement.dao.StoreManagementBaseDAOImpl;
import com.dwnews.storemanagement.dao.item.IItemManagementDAO;
import com.dwnews.storemanagement.entity.Items;

@Repository("itemManagementDAO")
public class ItemMangementDAOImpl extends 
	StoreManagementBaseDAOImpl<Items, Integer> implements IItemManagementDAO {
}
