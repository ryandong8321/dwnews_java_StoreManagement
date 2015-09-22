package com.dwnews.storemanagement.dao.category.impl;

import org.springframework.stereotype.Repository;

import com.dwnews.storemanagement.dao.StoreManagementBaseDAOImpl;
import com.dwnews.storemanagement.dao.category.ICategoryManagementDAO;
import com.dwnews.storemanagement.entity.Categories;

@Repository("categoryManagementDAO")
public class CategoryManagementDAOImpl extends 
StoreManagementBaseDAOImpl<Categories, Integer> implements ICategoryManagementDAO {

}
