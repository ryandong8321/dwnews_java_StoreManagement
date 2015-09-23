package com.dwnews.storemanagement.service.category;

import java.util.List;
import java.util.Map;

import com.dwnews.storemanagement.entity.Categories;
import com.dwnews.storemanagement.service.IStoreManagementBaseService;

public interface ICategoryManagementService extends IStoreManagementBaseService<Categories, Integer> {
	
	public List<Map<String,Object>> findCategories(Map<String, Object> parameters);
	
	public Map<String, Object> findCategories(Integer rowNum, Integer pageSize, Map<String, Object> parameters);
	
	public List<Map<String,Object>> findCategoryRank(Integer parentId, String ids, Integer notExistId);
	
	public String findCategoryParents(Integer categoryId, Integer parentId);
	
}
