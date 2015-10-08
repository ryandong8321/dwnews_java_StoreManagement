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
	public Map<String, Object> findItems(Integer rowNum, Integer pageSize, Map<String, Object> parameters, Map<String, String> sort) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("from Items its ");

		Pagination<Items> page = new Pagination<Items>();
		page.setFirst(rowNum);
		page.setPageSize(pageSize);

		if (parameters != null && !parameters.isEmpty()) {
			hql.append(" where ");
			
			String flag=parameters.get("flag").toString();
			String tag=parameters.get("tag").toString();
			
			param = new Object[parameters.size()-2];
			int ind = 0;
			for (String key : parameters.keySet()) {
				if (key.equals("flag")||key.equals("tag")){
					continue;
				}
				if (ind!=0){
					hql.append(flag);
				}
				hql.append(key);
				hql.append(tag);
				param[ind++] = flag.equals(" OR ")?"%"+parameters.get(key)+"%":parameters.get(key);
			}
		}
		
		if (sort!=null&&!sort.isEmpty()){
			hql.append(" order by ");
			hql.append(sort.get("sort"));
			hql.append(" ");
			hql.append(sort.get("order"));
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
				map.put("itemBrand", item.getBrand()==null?"":item.getBrand().getBrandName());
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

	@Override
	public List<Map<String,Object>> findAllItems(Integer selectedItemId) {
		List<Items> lst=this.getCurrentDAO().find("From Items its", new Object[0]);
		
		List<Map<String,Object>> result=null;
		if(lst!=null&&!lst.isEmpty()){
			result=new ArrayList<Map<String,Object>>();
			Map<String,Object> map=null;
			for (Items item:lst){
				map=new HashMap<String, Object>();
				map.put("id", item.getId());
				map.put("text", item.getItemName());
				if (selectedItemId!=null&&item.getId().equals(selectedItemId)){
					map.put("selected", "selected");
				}
				result.add(map);
			}
		}
		return result;
	}

}
