package com.dwnews.storemanagement.dao.brand.impl;

import org.springframework.stereotype.Repository;

import com.dwnews.storemanagement.dao.StoreManagementBaseDAOImpl;
import com.dwnews.storemanagement.dao.brand.IBrandManagementDAO;
import com.dwnews.storemanagement.entity.Brand;

@Repository("brandManagementDAO")
public class BrandManagementDAOImpl extends StoreManagementBaseDAOImpl<Brand, Integer> implements IBrandManagementDAO{

}
