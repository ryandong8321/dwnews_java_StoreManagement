package com.dwnews.storemanagement.service.department;

import java.util.List;
import java.util.Map;
import com.dwnews.storemanagement.entity.Departments;
import com.dwnews.storemanagement.service.IStoreManagementBaseService;

public interface IDepartmentManagementService extends IStoreManagementBaseService<Departments, Integer> {
	
	public List<Map<String,Object>> findDepartment(Map<String, Object> parameters);
	
	public Map<String, Object> findDepartment(Integer rowNum, Integer pageSize, Map<String, Object> parameters);
	
	public String findDepartmentParents(Integer departmentId, Integer parentId);
	
	public List<Map<String,Object>> findDepartmentsForTree(Map<String, Object> parameters);
	
	public boolean deleteDepartments(String departmentIds);

}
