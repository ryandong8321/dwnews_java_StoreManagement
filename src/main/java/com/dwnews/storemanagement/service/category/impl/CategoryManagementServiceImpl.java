package com.dwnews.storemanagement.service.category.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwnews.storemanagement.dao.Pagination;
import com.dwnews.storemanagement.dao.category.ICategoryManagementDAO;
import com.dwnews.storemanagement.entity.Categories;
import com.dwnews.storemanagement.service.StoreManagementBaseServiceImpl;
import com.dwnews.storemanagement.service.category.ICategoryManagementService;

@Service("categoryManagementService")
public class CategoryManagementServiceImpl
		extends StoreManagementBaseServiceImpl<Categories, Integer, ICategoryManagementDAO>
		implements ICategoryManagementService {

	@Autowired
	private ICategoryManagementDAO categoryManagementDAO;

	@Override
	protected ICategoryManagementDAO getCurrentDAO() {
		return this.categoryManagementDAO;
	}

	@Override
	public List<Map<String, Object>> findCategories(Map<String, Object> parameters) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("From Categories ca where 1=1");
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
		List<Categories> lst = this.getCurrentDAO().find(hql.toString(), param);
		if (lst != null && !lst.isEmpty()) {
			Map<String, Object> map = null;
			for (Categories category : lst) {
				map = new HashMap<String, Object>();
				map.put("id", category.getId());
				map.put("categoryName", category.getCategoryName());
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> findCategories(Integer rowNum, Integer pageSize, Map<String, Object> parameters) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("from Categories ca where 1=1");

		Pagination<Categories> page = new Pagination<>();
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
		Pagination<Categories> resultPage = this.getCurrentDAO().findPage(page, hql.toString(), param);

		if (resultPage.getRows() != null && !resultPage.getRows().isEmpty()) {
			Map<String, Object> map = null;
			for (Categories category : resultPage.getRows()) {
				map = new HashMap<String, Object>();
				map.put("id", category.getId());
				map.put("categoryName", category.getCategoryName());
				result.add(map);
			}
		}

		resultMap.put("total", page.getTotal());
		resultMap.put("rows", result);
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> findCategoryRank(Integer parentId, String ids, Integer notExistId) {
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		List<Integer> intIds = new ArrayList<Integer>();
		if (ids.contains(",")) {
			String[] arr = ids.split(",");
			for (String s : arr) {
				intIds.add(Integer.parseInt(s));
			}
		} else {
			intIds.add(Integer.parseInt(ids));
		}

		Object[] parameters = new Object[intIds.size() + 1];
		StringBuffer hql = new StringBuffer("From Categories ca where 1=1");
		hql.append(" and parentId = ?");
		parameters[0] = parentId;

		hql.append(" or id in (");
		for (int i=0;i<intIds.size();i++){
			if (i==0){
				hql.append("?");
			}else{
				hql.append(",?");
			}
			parameters[i+1]=intIds.get(i);
		}
		hql.append(") order by parentId");

		List<Categories> lst = this.getCurrentDAO().find(hql.toString(), parameters);
		
		//order category for show in jsp page
		Map<String, Object> map = null;
		Categories[] arrCagtegories=new Categories[intIds.size()];
		Categories category=null;
		for (int ind=0;ind<lst.size();ind++) {
			category=lst.get(ind);
			for (int i=0;i<intIds.size();i++) {
				if (category.getId()==intIds.get(i)){
					arrCagtegories[i]=category;
					lst.set(ind, null);
				}
			}
		}
		for(int i=0;i<arrCagtegories.length;i++){
			if (arrCagtegories[i]==null){
				continue;
			}
			category=arrCagtegories[i];
			map = new HashMap<String, Object>();
			map.put("id", category.getId());
			map.put("text", category.getCategoryName());
			map.put("disabled", "disabled");
			result.add(map);
		}
		
		if (lst != null && !lst.isEmpty()) {
			for (int ind=0;ind<lst.size();ind++) {
				if(lst.get(ind)==null){
					continue;
				}
				category=lst.get(ind);
				if (category.getId()==notExistId){
					continue;
				}
				map = new HashMap<String, Object>();
				map.put("id", category.getId());
				map.put("text", category.getCategoryName());
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public String findCategoryParents(Integer categoryId, Integer parentId) {
		return findParentId(parentId, "");
	}
	
	private String findParentId(Integer parentId,String parents){
		Categories category=this.getCurrentDAO().get(parentId);
		if (category!=null&&category.getParentId()!=-1){
			parents += findParentId(category.getParentId(), parents)+",";
		}
		parents+=category.getId();
		return parents;
	}

	@Override
	public List<Map<String, Object>> findCategoriesForTree(Map<String, Object> parameters) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("From Categories ca where 1=1");
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
		
		hql.append(" order by parentId");
		
		List<Categories> lst = this.getCurrentDAO().find(hql.toString(), param);
		if (lst != null && !lst.isEmpty()) {
			Map<String, Object> map = null;
			for (Categories category : lst) {
				map = new HashMap<String, Object>();
				map.put("id", category.getId());
				map.put("text", category.getCategoryName());
				map.put("parent", category.getParentId()==-1?"#":category.getParentId());
				result.add(map);
			}
		}
		return result;
	}
}
