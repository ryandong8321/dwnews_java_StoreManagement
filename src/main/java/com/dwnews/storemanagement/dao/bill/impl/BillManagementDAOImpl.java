package com.dwnews.storemanagement.dao.bill.impl;

import org.springframework.stereotype.Repository;

import com.dwnews.storemanagement.dao.StoreManagementBaseDAOImpl;
import com.dwnews.storemanagement.dao.bill.IBillManagementDAO;
import com.dwnews.storemanagement.entity.ItemsInputOutput;

@Repository("billManagementDAO")
public class BillManagementDAOImpl extends StoreManagementBaseDAOImpl<ItemsInputOutput, Integer> implements IBillManagementDAO {

}
