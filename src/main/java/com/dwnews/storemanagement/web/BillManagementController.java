package com.dwnews.storemanagement.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.dwnews.storemanagement.entity.Categories;
import com.dwnews.storemanagement.entity.Items;
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
		
		int rowNum = ServletRequestUtils.getIntParameter(request, "offset", 0), 
			showCount = ServletRequestUtils.getIntParameter(request, "limit", 0), 
			ocategory = ServletRequestUtils.getIntParameter(request, "ocategory", 0);
		
		String strSort=ServletRequestUtils.getStringParameter(request, "sort", null), 
			strOrder=ServletRequestUtils.getStringParameter(request, "order", null), 
			search=ServletRequestUtils.getStringParameter(request, "search", null);
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("operationCategory", ocategory);
		
		if (search!=null&&!search.equals("")){
			parameters=new HashMap<String, Object>();
			parameters.put("billItem", search);
			parameters.put("billDepartment", search);
			parameters.put("itemBarCode", search);
//			parameters.put("createTime", search);
		}
		
		Map<String, String> mapSort=null;
		if (strSort!=null&&!strSort.equals("")&&strOrder!=null&&!strOrder.equals("")){
			mapSort=new HashMap<String, String>();
			mapSort.put("sort", strSort);
			mapSort.put("order", strOrder);
		}
		
		Map<String,Object> data=storeBillManagementService.findStoreBillInformation(rowNum,showCount,parameters,mapSort);
		
		String result=JSONObject.fromObject(data).toString();
		logger.info("this is [initBillTable.do] data ["+result+"] ...");
		logger.info("this is [initBillTable.do] end ...");
		return result;
	}
	
	@RequestMapping(value = "/showbillinfo.do", method=RequestMethod.POST)
	public String showBillInfo(HttpServletRequest request, Integer billId, Integer ocategory) {
		logger.info("this is [showBillInfo.do] start ...");
		logger.info("this is [showBillInfo.do] billId ["+billId+"] ...");
		logger.info("this is [showBillInfo.do] ocategory ["+ocategory+"] ...");
		ItemsInputOutput bill=null;
		try{
			if (billId!=null&&billId!=-1){
				logger.info("this is [showBillInfo.do] find Bill ...");
				bill=storeBillManagementService.get(billId);
			}
		}catch(Exception ex){
			logger.info("this is [showBillInfo.do] find Bill failed...");
			ex.printStackTrace();
		}
		
		logger.info("this is [showBillInfo.do] data ["+bill+"] ...");
		
		request.setAttribute("bill", bill);
		request.setAttribute("ocategory", ocategory);
		logger.info("this is [showBillInfo.do] end ...");
		return "bill/billinfo";
	}
	
	@RequestMapping(value = "/getallitems.do",method=RequestMethod.POST)
	@ResponseBody
	public String getAllItems(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [getAllItems.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [getAllItems.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [getAllItems.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		String categoryId=null, billId=null;
		try {
			JSONObject json=JSONObject.fromString(data);
			categoryId = json.getString("categoryId")==null||json.getString("categoryId").equals("")||json.getString("categoryId").equals("null")
					?"-1":json.getString("categoryId");
			billId = json.getString("billId")==null||json.getString("billId").equals("")||json.getString("billId").equals("null")
					?"-1":json.getString("billId");
			logger.info("this is [getAllItems.do] show categoryId ["+categoryId+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		try{
			logger.info("this is [getAllItems.do] is finding Items...");
			List<Map<String,Object>> allItems=null;
			
			Integer itemId=null;
			if (billId!=null&&!billId.equals("-1")){
				ItemsInputOutput bill=storeBillManagementService.get(Integer.parseInt(billId));
				if (bill!=null&&bill.getBillItem()!=null){
					itemId=bill.getBillItem().getId();
				}
			}
			
			if (categoryId!=null&&categoryId.equals("-1")){
				allItems=itemManagementService.findAllItems(itemId);
			}else if (categoryId!=null){
				Categories category=categoryManagementService.get(Integer.parseInt(categoryId));
				allItems=new ArrayList<Map<String,Object>>();
				Map<String,Object> map=null;
				for (Items item:category.getItems()){
					map=new HashMap<String, Object>();
					map.put("id", item.getId());
					map.put("text", item.getItemName());
					if (itemId!=null){
						if (item.getId().equals(itemId)){
							map.put("selected", "selected");
						}
					}
					allItems.add(map);
				}
			}
			parameters.put("status", 1);
			parameters.put("data", allItems);
		}catch(Exception ex){
			logger.info("this is [getAllItems.do] find Items error ...");
			ex.printStackTrace();
			parameters.put("status", 0);
			parameters.put("data", "");
		}
		String result=JSONObject.fromObject(parameters).toString();
		logger.info("this is [getAllItems.do] result ["+result+"] ...");
		logger.info("this is [getAllItems.do] end ...");
		return result;
	}
	
	@RequestMapping(value = "/finditem.do",method=RequestMethod.POST)
	@ResponseBody
	public String findItem(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [findItem.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [findItem.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [findItem.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		String itemId=null;
		try {
			JSONObject json=JSONObject.fromString(data);
			itemId = json.getString("itemId")==null||json.getString("itemId").equals("")||json.getString("itemId").equals("null")
					?"-1":json.getString("itemId");
			logger.info("this is [findItem.do] show itemId ["+itemId+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		try{
			logger.info("this is [findItem.do] is finding Items...");
			Map<String, Object> map = new HashMap<String, Object>();
			if (itemId!=null&&!itemId.equals("-1")){
				Items item=itemManagementService.get(Integer.parseInt(itemId));
				if (item!=null){
					map.put("categoryId", item.getCategory()==null?null:item.getCategory().getId());
					map.put("categoryName", item.getCategory()==null?null:item.getCategory().getCategoryName());
					map.put("itemBarCode", item.getItemBarCode());
				}
			}
			parameters.put("status", 1);
			parameters.put("data", map);
		}catch(Exception ex){
			logger.info("this is [findItem.do] find Items error ...");
			ex.printStackTrace();
			parameters.put("status", 0);
			parameters.put("data", "");
		}
		String result=JSONObject.fromObject(parameters).toString();
		logger.info("this is [findItem.do] result ["+result+"] ...");
		logger.info("this is [findItem.do] end ...");
		return result;
	}
	
	@RequestMapping(value = "/savebill.do", method=RequestMethod.POST)
	public String saveBill(HttpServletRequest request, @ModelAttribute("bill") ItemsInputOutput bill, Integer billId) {
		logger.info("this is [saveBill.do] start ...");
		
		if (billId!=null&&billId!=0){
			bill.setId(billId);
		}
		
		Map<String, Object> result=new HashMap<String, Object>();
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
		logger.info("this is [saveBill.do] show result ["+result+"] ...");
		request.setAttribute("result", result.get("data"));
		request.setAttribute("bill", bill);
		return result.get("status").equals(1)?"forward:/billmanagement/billlist.do?ocategory="+bill.getOperationCategory():"forward:/billmanagement/showbillinfo.do";
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
