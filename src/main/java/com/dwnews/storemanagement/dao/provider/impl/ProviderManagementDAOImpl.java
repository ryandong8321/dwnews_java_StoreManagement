package com.dwnews.storemanagement.dao.provider.impl;

import org.springframework.stereotype.Repository;

import com.dwnews.storemanagement.dao.StoreManagementBaseDAOImpl;
import com.dwnews.storemanagement.dao.provider.IProviderManagementDAO;
import com.dwnews.storemanagement.entity.Providers;

@Repository("providerManagementDAO")
public class ProviderManagementDAOImpl extends StoreManagementBaseDAOImpl<Providers, Integer> implements IProviderManagementDAO{

}
