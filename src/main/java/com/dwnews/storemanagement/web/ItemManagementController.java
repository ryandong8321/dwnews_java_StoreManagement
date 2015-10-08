package com.dwnews.storemanagement.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.dwnews.storemanagement.entity.Brand;
import com.dwnews.storemanagement.entity.Items;
import com.dwnews.storemanagement.entity.Providers;
import com.dwnews.storemanagement.service.brand.IBrandManagementService;
import com.dwnews.storemanagement.service.category.ICategoryManagementService;
import com.dwnews.storemanagement.service.item.IItemManagementService;
import com.dwnews.storemanagement.service.provider.IProviderManagementService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/itemmanagement")
public class ItemManagementController {

	protected static Logger logger = LoggerFactory.getLogger(ItemManagementController.class);

	@Autowired
	private IProviderManagementService providerManagementService;
	
	@Autowired
	private IBrandManagementService brandManagementService;
	
	@Autowired
	private IItemManagementService itemManagementService;
	
	@Autowired
	private ICategoryManagementService categoryManagementService;
	
	@RequestMapping(value = "/itemslist.do")
	public String itemsList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [itemsList.do] start ...");
		logger.info("this is [itemsList.do] end ...");
		return "items/itemslist";
	}
	
	@RequestMapping(value = "/showcategoriestree.do")
	public String showCategoriesTree(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [showCategoriesTree.do] start ...");
		logger.info("this is [showCategoriesTree.do] end ...");
		return "items/categoriestree";
	}
	
	@RequestMapping(value = "/inititemstable.do")
	@ResponseBody
	public String initItemsTable(HttpServletRequest request) {
		logger.info("this is [initItemsTable.do] start ...");
		
		int rowNum = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int showCount = ServletRequestUtils.getIntParameter(request, "limit", 0);
		
		String strSort=ServletRequestUtils.getStringParameter(request, "sort", null);
		String strOrder=ServletRequestUtils.getStringParameter(request, "order", null);
		
		String search=ServletRequestUtils.getStringParameter(request, "search", null);
		String filters=ServletRequestUtils.getStringParameter(request, "filters", null);
		
		Map<String, Object> parameters=null;
		if (search!=null&&!search.equals("")){
			parameters=new HashMap<String, Object>();
			parameters.put("flag", " OR ");
			parameters.put("tag", " like ?");
			parameters.put("itemName", search);
			parameters.put("itemBarCode", search);
		}
		
		if (filters!=null&&!filters.equals("")){
			parameters=new HashMap<String, Object>();
			parameters.put("flag", " AND ");
			parameters.put("tag", " = ?");
			JSONObject json=JSONObject.fromObject(filters);
			Iterator<?> it=json.keys();
			String key=null;
			while(it.hasNext()){
				key=it.next().toString();
				parameters.put(key, json.get(key));
			}
		}
		
		Map<String, String> mapSort=null;
		if (strSort!=null&&!strSort.equals("")&&strOrder!=null&&!strOrder.equals("")){
			mapSort=new HashMap<String, String>();
			mapSort.put("sort", strSort);
			mapSort.put("order", strOrder);
		}
		
		Map<String,Object> data=itemManagementService.findItems(rowNum,showCount,parameters,mapSort);
		
		String result=JSONObject.fromObject(data).toString();
		logger.info("this is [initItemsTable.do] data ["+result+"] ...");
		logger.info("this is [initItemsTable.do] end ...");
		return result;
	}
	
	@RequestMapping(value = "/showiteminfo.do", method=RequestMethod.POST)
	public String showItemInfo(HttpServletRequest request, String itemId) {
		logger.info("this is [showiteminfo.do] start ...");
		logger.info("this is [showiteminfo.do] itemId ["+itemId+"] ...");
		Items item=null;
		try{
			logger.info("this is [showiteminfo.do] find Item ...");
			item=itemManagementService.get(Integer.parseInt(itemId));
		}catch(Exception ex){
			logger.info("this is [showiteminfo.do] find Item failed...");
			ex.printStackTrace();
		}
		
		logger.info("this is [showiteminfo.do] data ["+item+"] ...");
		
		request.setAttribute("item", item);
		logger.info("this is [showiteminfo.do] end ...");
		return "items/iteminfo";
	}
	
	@RequestMapping(value = "/getallbrand.do", method=RequestMethod.POST)
	@ResponseBody
	public String getAllBrand(HttpServletRequest request, @RequestBody String data) {
		logger.info("this is [getAllBrand.do] start ...");
		Integer itemId=0;
		Map<String, Object> result=new HashMap<String, Object>();
		
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [getAllBrand.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [getAllBrand.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		try{
			JSONObject json=JSONObject.fromString(data);
			itemId=Integer.parseInt(
					json.getString("itemId")==null||json.getString("itemId").equals("")||json.getString("itemId").equals("null")
						?"0":json.getString("itemId"));
		}catch(Exception ex){
			logger.info("this is [saveProvider.do] get parameter error ...");
			result.put("status", -1);
			result.put("data", "parameters error");
			ex.printStackTrace();
		}
		
		List<Map<String,Object>> allBrands=brandManagementService.findBrands(null);
		
		if (itemId!=0){
			Items item=itemManagementService.get(itemId);
			if (item.getBrand()!=null){
				for(Map<String, Object> map:allBrands){
					if (map.get("id").equals(item.getBrand().getId())){
						//disabled: $option.prop('disabled'),
				        //selected: $option.prop('selected'),
						map.put("selected", "selected");
						break;
					}
				}
			}
		}
		
		result.put("status", 1);
		result.put("data", allBrands);
		
		String json=JSONObject.fromObject(result).toString();
		logger.info("this is [getAllBrand.do] data ["+json+"] ...");
		logger.info("this is [getAllBrand.do] end ...");
		return json;
	}
	
	@RequestMapping(value = "/getallproviders.do", method=RequestMethod.POST)
	@ResponseBody
	public String getAllProviders(HttpServletRequest request, @RequestBody String data) {
		logger.info("this is [getAllProviders.do] start ...");
		Integer brandId=0, itemId=0;
		Map<String, Object> result=new HashMap<String, Object>();
		
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [getAllProviders.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [getAllProviders.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		try{
			JSONObject json=JSONObject.fromString(data);
			brandId=Integer.parseInt(
					json.getString("brandId")==null||json.getString("brandId").equals("")||json.getString("brandId").equals("null")
						?"0":json.getString("brandId"));
			itemId=Integer.parseInt(
					json.getString("itemId")==null||json.getString("itemId").equals("")||json.getString("itemId").equals("null")
						?"0":json.getString("itemId"));
		}catch(Exception ex){
			logger.info("this is [getAllProviders.do] get parameter error ...");
			result.put("status", -1);
			result.put("data", "parameters error");
			ex.printStackTrace();
		}
		
		List<Map<String,Object>> allProviders=new ArrayList<Map<String,Object>>();
		if (brandId!=0){
			Items item=null;
			try{
				item=itemManagementService.get(itemId);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			Map<String,Object> map=null;
			List<Providers> lstProviders=item==null?null:item.getProviders();
			Brand brand=brandManagementService.get(brandId);
			for (Providers provider:brand.getProviders()){
				map=new HashMap<String,Object>();
				map.put("id", provider.getId());
				map.put("text", provider.getProviderName());
				if (lstProviders!=null&&!lstProviders.isEmpty()){
					for (Providers pder:lstProviders){
						if (provider.getId().equals(pder.getId())){
							map.put("selected", "selected");
							break;
						}
					}
				}
				allProviders.add(map);
			}
		}
		
		result.put("status", 1);
		result.put("data", allProviders);
		
		String json=JSONObject.fromObject(result).toString();
		logger.info("this is [getAllProviders.do] data ["+json+"] ...");
		logger.info("this is [getAllProviders.do] end ...");
		return json;
	}
	
	@RequestMapping(value = "/saveitem.do", method=RequestMethod.POST)
	public String saveItem(HttpServletRequest request, @ModelAttribute("item") Items item, Integer itemId, String brandId, String providerIds) {
		logger.info("this is [saveItem.do] start ...");
		
		if (itemId!=null&&itemId!=0){
			item.setId(itemId);
		}
		
		logger.info("this is [saveItem.do] item {"+item+"} ...");
		logger.info("this is [saveItem.do] brandId {"+brandId+"} ...");
		logger.info("this is [saveItem.do] providerIds {"+providerIds+"} ...");
		
		Map<String, Object> result=new HashMap<String, Object>();
		
		if (brandId!=null&&!brandId.equals("")){
			try{
				item.setBrand(brandManagementService.get(Integer.parseInt(brandId)));
			}catch(Exception ex){
				ex.printStackTrace();
				logger.info("this is [saveItem.do] find brand failed ...");
				result.put("status", 0);
				result.put("data", "save failed, try again!");
			}
		}
		
		if (result.isEmpty()){
			if (item.getCategory()!=null&&item.getCategory().getId()!=null){
				try {
					item.setCategory(categoryManagementService.get(item.getCategory().getId()));
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("this is [saveItem.do] find category failed ...");
					result.put("status", 0);
					result.put("data", "save failed, try again!");
				}
			}
		}
		
		if (result.isEmpty()){
			try {
				List<Providers> lstProvides=new ArrayList<Providers>();
				if (providerIds!=null&&!providerIds.equals("")){
					String[] arr=null;
					if (!providerIds.contains(",")){
						arr=new String[1];
						arr[0]=providerIds;
					}else{
						arr=providerIds.split(",");
					}
					
					for (int ind=0;ind<arr.length;ind++){
						lstProvides.add(providerManagementService.get(Integer.parseInt(arr[ind])));
					}
				}
				item.setProviders(lstProvides);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				logger.info("this is [saveItem.do] find providers failed ...");
				result.put("status", 0);
				result.put("data", "save failed, try again!");
			}
		}
		
		if (result.isEmpty()){
			try{
				logger.info("this is [saveItem.do] is saving ...");
				itemManagementService.save(item);
				logger.info("this is [saveItem.do] save item done ...");
				result.put("status", 1);
				result.put("data", "save success!");
			}catch(Exception ex){
				logger.info("this is [saveItem.do] save item error ...");
				result.put("status", 0);
				result.put("data", "save failed, try again!");
				ex.printStackTrace();
			}
		}
		logger.info("this is [saveItem.do] show result ["+result+"] ...");
		request.setAttribute("result", result.get("data"));
		request.setAttribute("item", item);
		return result.get("status").equals(1)?"forward:/itemmanagement/itemslist.do":"forward:/itemmanagement/showitem.do";
	}
	
	@RequestMapping(value = "/deleteitems.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteItems(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [deleteItems.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [deleteItems.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [deleteItems.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		JSONObject json=JSONObject.fromString(data);
		String ids=json.getString("itemsIds")==null||json.getString("itemsIds").equals("")||json.getString("itemsIds").equals("null")
				?"-1":json.getString("itemsIds");
		logger.info("this is [deleteItems.do] show itemsIds ["+ids+"]");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		try{
			
			logger.info("this is [deleteItems.do] is deleting ...");
			itemManagementService.deleteItems(ids);
			parameters.put("status", 1);
			parameters.put("data", "operation success.");
		}catch(Exception ex){
			logger.info("this is [deleteItems.do] delete error ...");
			ex.printStackTrace();
			parameters.put("status", 0);
			parameters.put("data", "operation failed, try again please.");
		}
		
		logger.info("this is [deleteItems.do] end ...");
		return parameters;
	}
}
