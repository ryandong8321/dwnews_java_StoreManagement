package com.dwnews.storemanagement.service.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwnews.storemanagement.dao.Pagination;
import com.dwnews.storemanagement.dao.provider.IProviderManagementDAO;
import com.dwnews.storemanagement.entity.Providers;
import com.dwnews.storemanagement.service.StoreManagementBaseServiceImpl;
import com.dwnews.storemanagement.service.provider.IProviderManagementService;

@Service("providerManagementService")
public class ProviderManagementServiceImpl 
	extends StoreManagementBaseServiceImpl<Providers, Integer, IProviderManagementDAO> implements IProviderManagementService{

	@Autowired
	private IProviderManagementDAO providerManagementDAO;
	
	@Override
	protected IProviderManagementDAO getCurrentDAO() {
		return this.providerManagementDAO;
	}

	@Override
	public List<Map<String, Object>> findProviders(Map<String, Object> parameters) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("From Provider pd where 1=1");
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
		List<Providers> lst = this.getCurrentDAO().find(hql.toString(), param);
		if (lst != null && !lst.isEmpty()) {
			Map<String, Object> map = null;
			for (Providers provider : lst) {
				map = new HashMap<String, Object>();
				map.put("id", provider.getId());
				map.put("providerName", provider.getProviderName());
				map.put("contactName", provider.getContactName());
				map.put("contactPhoneNumber", provider.getContactPhoneNumber());
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> findProviders(Integer rowNum, Integer pageSize, Map<String, Object> parameters) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("from Providers pd where 1=1");

		Pagination<Providers> page = new Pagination<Providers>();
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
			for (Providers provider : page.getRows()) {
				map = new HashMap<String, Object>();
				map.put("id", provider.getId());
				map.put("providerName", provider.getProviderName());
				map.put("contactName", provider.getContactName());
				map.put("contactPhoneNumber", provider.getContactPhoneNumber());
				result.add(map);
			}
		}

		resultMap.put("total", page.getTotal());
		resultMap.put("rows", result);
		return resultMap;
	}

	@Override
	public boolean deleteProviders(String providerIds) {
		String[] ids=providerIds.split(",");
		Object[] obj=new Object[ids.length];
		StringBuffer hql=new StringBuffer("delete from Providers pd where id in (");
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
