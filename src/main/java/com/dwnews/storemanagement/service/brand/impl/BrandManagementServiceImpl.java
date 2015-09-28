package com.dwnews.storemanagement.service.brand.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwnews.storemanagement.dao.Pagination;
import com.dwnews.storemanagement.dao.brand.IBrandManagementDAO;
import com.dwnews.storemanagement.entity.Brand;
import com.dwnews.storemanagement.service.StoreManagementBaseServiceImpl;
import com.dwnews.storemanagement.service.brand.IBrandManagementService;

@Service("brandManagementService")
public class BrandManagementServiceImpl 
	extends StoreManagementBaseServiceImpl<Brand, Integer, IBrandManagementDAO> implements IBrandManagementService{

	@Autowired
	private IBrandManagementDAO brandManagementDAO;
	
	@Override
	protected IBrandManagementDAO getCurrentDAO() {
		return this.brandManagementDAO;
	}

	@Override
	public List<Map<String, Object>> findBrands(Map<String, Object> parameters) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("From Brand bra where 1=1");
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
		List<Brand> lst = this.getCurrentDAO().find(hql.toString(), param);
		if (lst != null && !lst.isEmpty()) {
			Map<String, Object> map = null;
			for (Brand brand : lst) {
				map = new HashMap<String, Object>();
				map.put("id", brand.getId());
				map.put("text", brand.getBrandName());
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public boolean deleteBrands(String brandIds) {
		String[] ids=brandIds.split(",");
		Object[] obj=new Object[ids.length];
		StringBuffer hql=new StringBuffer("delete from Brand bra where id in (");
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

	@Override
	public Map<String, Object> findBrands(Integer rowNum, Integer pageSize, Map<String, Object> parameters) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("from Brand bra where 1=1");

		Pagination<Brand> page = new Pagination<Brand>();
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
		page = this.getCurrentDAO().findPage(page, hql.toString(), param);

		if (page.getRows() != null && !page.getRows().isEmpty()) {
			Map<String, Object> map = null;
			for (Brand brand : page.getRows()) {
				map = new HashMap<String, Object>();
				map.put("id", brand.getId());
				map.put("brandName", brand.getBrandName());
				result.add(map);
			}
		}

		resultMap.put("total", page.getTotal());
		resultMap.put("rows", result);
		return resultMap;
	}

}
