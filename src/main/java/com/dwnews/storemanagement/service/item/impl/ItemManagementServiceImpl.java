package com.dwnews.storemanagement.service.item.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwnews.storemanagement.dao.Pagination;
import com.dwnews.storemanagement.dao.item.IItemManagementDAO;
import com.dwnews.storemanagement.entity.Items;
import com.dwnews.storemanagement.service.StoreManagementBaseServiceImpl;
import com.dwnews.storemanagement.service.item.IItemManagementService;

@Service("itemManagementService")
public class ItemManagementServiceImpl extends StoreManagementBaseServiceImpl<Items, Integer, IItemManagementDAO> implements IItemManagementService{

	@Autowired
	private IItemManagementDAO itemManagementDAO;
	
	@Override
	protected IItemManagementDAO getCurrentDAO() {
		return this.itemManagementDAO;
	}

	@Override
	public Map<String, Object> findItems(Integer rowNum, Integer pageSize, Map<String, Object> parameters) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("from Items its where 1=1");

		Pagination<Items> page = new Pagination<Items>();
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
			for (Items item : page.getRows()) {
				map = new HashMap<String, Object>();
				map.put("id", item.getId());
				map.put("itemName", "<a href='javascript:showItemInfo()'>"+item.getItemName()+"</a>");
				map.put("itemPrice", item.getItemPrice());
				map.put("itemStandard", item.getItemStandard());
				map.put("itemStoreCount", item.getItemStoreCount());
				map.put("itemBarCode", item.getItemBarCode());
				map.put("itemCategory", item.getCategory().getCategoryName());
				map.put("itemBrand", item.getBrand().getBrandName());
				result.add(map);
			}
		}

		resultMap.put("total", page.getTotal());
		resultMap.put("rows", result);
		return resultMap;
	}

	@Override
	public boolean deleteItems(String itemIds) {
		String[] ids=itemIds.split(",");
		Object[] obj=new Object[ids.length];
		StringBuffer hql=new StringBuffer("delete from Items its where id in (");
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
