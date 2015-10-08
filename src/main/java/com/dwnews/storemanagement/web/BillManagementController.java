package com.dwnews.storemanagement.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dwnews.storemanagement.entity.ItemsInputOutput;
import com.dwnews.storemanagement.service.bill.IStoreBillManagementService;
import com.dwnews.storemanagement.service.category.ICategoryManagementService;
import com.dwnews.storemanagement.service.item.IItemManagementService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/billmanagement")
public class BillManagementController {
	
	protected static Logger logger = LoggerFactory.getLogger(BillManagementController.class);
	
	@Autowired
	private IItemManagementService itemManagementService;
	
	@Autowired
	private ICategoryManagementService categoryManagementService;
	
	@Autowired
	private IStoreBillManagementService storeBillManagementService;
	
	@RequestMapping(value = "/billlist.do")
	public String billList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [billList.do] start ...");
		String ocategory=ServletRequestUtils.getStringParameter(request, "ocategory", null);
		logger.info("this is [billList.do] ocategory ["+ocategory+"] ...");
		modelMap.addAttribute("ocategory", ocategory);
		logger.info("this is [billList.do] end ...");
		return "bill/billlist";
	}
	
	@RequestMapping(value = "/initbilltable.do")
	@ResponseBody
	public String initBillTable(HttpServletRequest request) {
		logger.info("this is [initBillTable.do] start ...");
		
		int rowNum = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int showCount = ServletRequestUtils.getIntParameter(request, "limit", 0);
		
		String strSort=ServletRequestUtils.getStringParameter(request, "sort", null), 
				strOrder=ServletRequestUtils.getStringParameter(request, "order", null), 
				ocategory=ServletRequestUtils.getStringParameter(request, "ocategory", null), 
				itemName=null, startDate=null, endDate=null, departmentId=null, operationCategory=null, verifyStatus=null;
		
//		String search=ServletRequestUtils.getStringParameter(request, "search", null);
//		String filters=ServletRequestUtils.getStringParameter(request, "filters", null);
		Map<String, Object> parameters=null;
		if (ocategory!=null&&!ocategory.equals("")){
			parameters=new HashMap<String, Object>();
			parameters.put("operationCategory", ocategory);
		}
		
		
//		if (search!=null&&!search.equals("")){
//			parameters=new HashMap<String, Object>();
//			parameters.put("flag", " OR ");
//			parameters.put("tag", " like ?");
//			parameters.put("itemName", search);
//			parameters.put("itemBarCode", search);
//		}
//		
//		if (filters!=null&&!filters.equals("")){
//			parameters=new HashMap<String, Object>();
//			parameters.put("flag", " AND ");
//			parameters.put("tag", " = ?");
//			JSONObject json=JSONObject.fromObject(filters);
//			Iterator<?> it=json.keys();
//			String key=null;
//			while(it.hasNext()){
//				key=it.next().toString();
//				parameters.put(key, json.get(key));
//			}
//		}
		
		Map<String, String> mapSort=null;
		if (strSort!=null&&!strSort.equals("")&&strOrder!=null&&!strOrder.equals("")){
			mapSort=new HashMap<String, String>();
			mapSort.put("sort", strSort);
			mapSort.put("order", strOrder);
		}
		
		Map<String,Object> data=itemManagementService.findItems(rowNum,showCount,parameters,mapSort);
		
		String result=JSONObject.fromObject(data).toString();
		logger.info("this is [initBillTable.do] data ["+result+"] ...");
		logger.info("this is [initBillTable.do] end ...");
		return result;
	}
	
	@RequestMapping(value = "/showbillinfo.do", method=RequestMethod.POST)
	public String showBillInfo(HttpServletRequest request, String billId) {
		logger.info("this is [showBillInfo.do] start ...");
		logger.info("this is [showBillInfo.do] billId ["+billId+"] ...");
		ItemsInputOutput bill=null;
		try{
			logger.info("this is [showBillInfo.do] find Bill ...");
			bill=storeBillManagementService.get(Integer.parseInt(billId));
		}catch(Exception ex){
			logger.info("this is [showBillInfo.do] find Item failed...");
			ex.printStackTrace();
		}
		
		logger.info("this is [showBillInfo.do] data ["+bill+"] ...");
		
		request.setAttribute("bill", bill);
		logger.info("this is [showBillInfo.do] end ...");
		return "bill/billinfo";
	}
	
	@RequestMapping(value = "/savebill.do", method=RequestMethod.POST)
	public String saveBill(HttpServletRequest request, @ModelAttribute("bill") ItemsInputOutput bill, Integer billId, 
			String categoryId, String departmentId) {
		logger.info("this is [saveBill.do] start ...");
		
		if (billId!=null&&billId!=0){
			bill.setId(billId);
		}
		
		logger.info("this is [saveBill.do] bill {"+bill+"} ...");
		logger.info("this is [saveBill.do] categoryId {"+categoryId+"} ...");
		logger.info("this is [saveBill.do] departmentId {"+departmentId+"} ...");
		
		Map<String, Object> result=new HashMap<String, Object>();
		
		if (categoryId!=null&&!categoryId.equals("")){
			try{
				bill.setItemCategory(categoryManagementService.get(Integer.parseInt(categoryId)));
			}catch(Exception ex){
				ex.printStackTrace();
				logger.info("this is [saveBill.do] find category failed ...");
				result.put("status", 0);
				result.put("data", "save failed, try again!");
			}
		}
		
//		if (result.isEmpty()){
//			if (bill.getCategory()!=null&&bill.getCategory().getId()!=null){
//				try {
//					bill.setCategory(categoryManagementService.get(bill.getCategory().getId()));
//				} catch (Exception e) {
//					e.printStackTrace();
//					logger.info("this is [saveBill.do] find category failed ...");
//					result.put("status", 0);
//					result.put("data", "save failed, try again!");
//				}
//			}
//		}
		
		if (result.isEmpty()){
			try{
				logger.info("this is [saveBill.do] is saving ...");
				storeBillManagementService.save(bill);
				logger.info("this is [saveBill.do] save bill done ...");
				result.put("status", 1);
				result.put("data", "save success!");
			}catch(Exception ex){
				logger.info("this is [saveBill.do] save bill error ...");
				result.put("status", 0);
				result.put("data", "save failed, try again!");
				ex.printStackTrace();
			}
		}
		logger.info("this is [saveBill.do] show result ["+result+"] ...");
		request.setAttribute("result", result.get("data"));
		request.setAttribute("item", bill);
		return result.get("status").equals(1)?"forward:/billmanagement/billlist.do":"forward:/billmanagement/showbill.do";
	}
	
	@RequestMapping(value = "/deletebill.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteBill(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [deleteBill.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [deleteBill.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [deleteBill.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		JSONObject json=JSONObject.fromString(data);
		String ids=json.getString("billIds")==null||json.getString("billIds").equals("")||json.getString("billIds").equals("null")
				?"-1":json.getString("billIds");
		logger.info("this is [deleteBill.do] show billIds ["+ids+"]");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		try{
			
			logger.info("this is [deleteBill.do] is deleting ...");
			storeBillManagementService.deleteBill(ids);
			parameters.put("status", 1);
			parameters.put("data", "operation success.");
		}catch(Exception ex){
			logger.info("this is [deleteBill.do] delete error ...");
			ex.printStackTrace();
			parameters.put("status", 0);
			parameters.put("data", "operation failed, try again please.");
		}
		
		logger.info("this is [deleteBill.do] end ...");
		return parameters;
	}

}
