package com.dwnews.storemanagement.service.bill.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwnews.storemanagement.dao.Pagination;
import com.dwnews.storemanagement.dao.bill.IBillManagementDAO;
import com.dwnews.storemanagement.entity.ItemsInputOutput;
import com.dwnews.storemanagement.service.StoreManagementBaseServiceImpl;
import com.dwnews.storemanagement.service.bill.IStoreBillManagementService;

@Service("storeBillManagementService")
public class StoreBillManagementServiceImpl 
	extends StoreManagementBaseServiceImpl<ItemsInputOutput, Integer, IBillManagementDAO> 
	implements IStoreBillManagementService {
	
	private final String _verify_wait="待审批", _verify_confirm="审批通过", _verify_pass="审批未通过";
	
	@Autowired
	private IBillManagementDAO billManagementDAO;
	
	@Override
	protected IBillManagementDAO getCurrentDAO() {
		return this.billManagementDAO;
	}

	@Override
	public Map<String, Object> findStoreBillInformation(Integer rowNum, Integer pageSize,
			Map<String, Object> parameters, Map<String, String> sort) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Object[] param = null;
		StringBuffer hql = new StringBuffer("from ItemsInputOutput iio ");

		Pagination<ItemsInputOutput> page = new Pagination<ItemsInputOutput>();
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
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", -1);
			map.put("billItemName", "<input type='text' id='itemName' name='itemName' />");
			map.put("billDepartmentName", "<input type='text' id='departmentName' name='departmentName' />");
			map.put("billItemBarCode", "<input type='text' id='itemBarCode' name='itemBarCode' />");
			map.put("billCount", "<input type='text' id='billCount' name='billCount' />");
			map.put("billOperationTime", "<input type='text' id='startDate' name='startDate' />");
			map.put("billVerify", "<select id='verifyStatus' name='verifyStatus'><option value='-1'>请选择</option><option value='0'>待审批</option><option value='1'>审批通过</option><option value='2'>审批未通过</option></select>");
			result.add(map);
			
			for (ItemsInputOutput iio : page.getRows()) {
				map = new HashMap<String, Object>();
				map.put("id", iio.getId());
				map.put("billItemName", "<a href='javascript:showBillInfo()'>"+iio.getBillItem().getItemName()+"</a>");
				map.put("billDepartmentName", iio.getBillDepartment().getDepartmentName());
				map.put("billItemBarCode", iio.getBillItem().getItemBarCode());
				map.put("billCount", iio.getBillCount());
				map.put("billOperationTime", iio.getCreateTime());
				map.put("billVerify", getVerifyStatus(iio.getBillVerify()));
				result.add(map);
			}
		}

		resultMap.put("total", page.getTotal());
		resultMap.put("rows", result);
		return resultMap;
	}

	@Override
	public boolean deleteBill(String billIds) {
		return false;
	}
	
	private String getVerifyStatus(Integer billVerify){
		String status=null;
		switch (billVerify){
		case 0:
			status=_verify_wait;
		case 1:
			status=_verify_confirm;
		case 2:
			status=_verify_pass;
		}
		return status;
	}
}
