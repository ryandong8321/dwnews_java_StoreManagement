package com.dwnews.storemanagement.dao.department.impl;

import org.springframework.stereotype.Repository;

import com.dwnews.storemanagement.dao.StoreManagementBaseDAOImpl;
import com.dwnews.storemanagement.dao.department.IDepartmentManagementDAO;
import com.dwnews.storemanagement.entity.Departments;

@Repository("departmentManagementDAO")
public class DepartmentManagementDAOImpl extends 
	StoreManagementBaseDAOImpl<Departments, Integer> implements IDepartmentManagementDAO{

}
