package com.dwnews.storemanagement.service.department.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwnews.storemanagement.dao.Pagination;
import com.dwnews.storemanagement.dao.department.IDepartmentManagementDAO;
import com.dwnews.storemanagement.entity.Departments;
import com.dwnews.storemanagement.service.StoreManagementBaseServiceImpl;
import com.dwnews.storemanagement.service.department.IDepartmentManagementService;

@Service("departmentManagementService")
public class DepartmentManagementServiceImpl 
	extends StoreManagementBaseServiceImpl<Departments, Integer, IDepartmentManagementDAO> 
	implements IDepartmentManagementService {
	
	@Autowired
	private IDepartmentManagementDAO departmentManagementDAO;

	@Override
	protected IDepartmentManagementDAO getCurrentDAO() {
		return this.departmentManagementDAO;
	}

	@Override
	public List<Map<String, Object>> findDepartment(Map<String, Object> parameters) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("From Departments dept where 1=1");
		if (parameters != null && !parameters.isEmpty()) {
			param = new Object[parameters.size()];
			int ind = 0;
			for (String key : parameters.keySet()) {
				hql.append(" and ");
				hql.append(key);
				hql.append(" = ?");
				param[ind++] = parameters.get(key);
			}
		}
		List<Departments> lst = this.getCurrentDAO().find(hql.toString(), param);
		if (lst != null && !lst.isEmpty()) {
			Map<String, Object> map = null;
			for (Departments dept : lst) {
				map = new HashMap<String, Object>();
				map.put("id", dept.getId());
				map.put("departmentName", dept.getDepartmentName());
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> findDepartment(Integer rowNum, Integer pageSize, Map<String, Object> parameters) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("from Departments dept where 1=1");

		Pagination<Departments> page = new Pagination<>();
		page.setFirst(rowNum);
		page.setPageSize(pageSize);

		if (parameters != null && !parameters.isEmpty()) {
			param = new Object[parameters.size()];
			int ind = 0;
			for (String key : parameters.keySet()) {
				hql.append(" and ");
				hql.append(key);
				hql.append(" = ?");
				param[ind++] = parameters.get(key);
			}
		}
		Pagination<Departments> resultPage = this.getCurrentDAO().findPage(page, hql.toString(), param);

		if (resultPage.getRows() != null && !resultPage.getRows().isEmpty()) {
			Map<String, Object> map = null;
			for (Departments dept : resultPage.getRows()) {
				map = new HashMap<String, Object>();
				map.put("id", dept.getId());
				map.put("departmentName", dept.getDepartmentName());
				result.add(map);
			}
		}

		resultMap.put("total", page.getTotal());
		resultMap.put("rows", result);
		return resultMap;
	}

	@Override
	public String findDepartmentParents(Integer departmentId, Integer parentId) {
		return findParentId(parentId, "");
	}
	
	private String findParentId(Integer parentId,String parents){
		Departments dept=this.getCurrentDAO().get(parentId);
		if (dept!=null&&dept.getParentId()!=-1){
			parents += findParentId(dept.getParentId(), parents)+",";
		}
		parents+=dept.getId();
		return parents;
	}

	@Override
	public List<Map<String, Object>> findDepartmentsForTree(Map<String, Object> parameters) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("From Departments dept where 1=1");
		if (parameters != null && !parameters.isEmpty()) {
			param = new Object[parameters.size()];
			int ind = 0;
			for (String key : parameters.keySet()) {
				hql.append(" and ");
				hql.append(key);
				hql.append(" = ?");
				param[ind++] = parameters.get(key);
			}
		}
		
		hql.append(" order by parentId , id");
		
		List<Departments> lst = this.getCurrentDAO().find(hql.toString(), param);
		if (lst != null && !lst.isEmpty()) {
			Map<String, Object> map = null;
			for (Departments dept : lst) {
				map = new HashMap<String, Object>();
				map.put("id", dept.getId());
				map.put("text", dept.getDepartmentName());
				map.put("parent", dept.getParentId()==-1?"#":dept.getParentId());
				if (dept.getParentId()==-1){
					Map<String,Object> state=new HashMap<String,Object>();
					state.put("opened", true);
					state.put("selected", true);
					map.put("state", state);
				}
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public boolean deleteDepartments(String departmentIds) {
		String[] ids=departmentIds.split(",");
		Object[] obj=new Object[ids.length];
		StringBuffer hql=new StringBuffer("delete from Departments dept where id in (");
		for (int i=0;i<ids.length;i++){
			if (i==0){
				hql.append("?");
			}else{
				hql.append(",?");
			}
			obj[i]=Integer.parseInt(ids[i]);
		}
		hql.append(")");
		
		boolean flag=true;
		try{
			this.getCurrentDAO().batchExecute(hql.toString(), obj);
		}catch(Exception ex){
			ex.printStackTrace();
			flag=false;
		}
		return flag;
	}

}
