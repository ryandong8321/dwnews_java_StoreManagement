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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwnews.storemanagement.entity.Brand;
import com.dwnews.storemanagement.entity.Providers;
import com.dwnews.storemanagement.service.brand.IBrandManagementService;
import com.dwnews.storemanagement.service.provider.IProviderManagementService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/providermanagement")
public class ProviderManagementController {
	
	protected static Logger logger = LoggerFactory.getLogger(ProviderManagementController.class);

	@Autowired
	private IProviderManagementService providerManagementService;
	
	@Autowired
	private IBrandManagementService brandManagementService;
	
	@RequestMapping(value = "/providerslist.do")
	public String providersList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [providersList.do] start ...");
		logger.info("this is [providersList.do] end ...");
		return "provider/providerslist";
	}
	
	@RequestMapping(value = "/initproviderstable.do")
	@ResponseBody
	public String initProvidersTable(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [initProvidersTable.do] start ...");
		
		int rowNum = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int showCount = ServletRequestUtils.getIntParameter(request, "limit", 0);
		
		Map<String,Object> data=providerManagementService.findProviders(rowNum,showCount,null);
		
		String result=JSONObject.fromObject(data).toString();
		logger.info("this is [initProvidersTable.do] data ["+result+"] ...");
		logger.info("this is [initProvidersTable.do] end ...");
		return result;
	}
	
	@RequestMapping(value = "/getallbrand.do", method=RequestMethod.POST)
	@ResponseBody
	public String getAllBrand(HttpServletRequest request, @RequestBody String data) {
		logger.info("this is [getAllBrand.do] start ...");
		Integer providerId=0;
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
			providerId=Integer.parseInt(
					json.getString("providerId")==null||json.getString("providerId").equals("")||json.getString("providerId").equals("null")
						?"0":json.getString("providerId"));
		}catch(Exception ex){
			logger.info("this is [saveProvider.do] get parameter error ...");
			result.put("status", -1);
			result.put("data", "parameters error");
			ex.printStackTrace();
		}
		
		List<Map<String,Object>> allBrands=brandManagementService.findBrands(null);
		
		if (providerId!=0){
			Providers provider=providerManagementService.get(providerId);
			List<Brand> brands=provider.getBrands();
			if (brands!=null&&!brands.isEmpty()){
				for (Brand brand:brands){
					for(Map<String, Object> map:allBrands){
						if (map.get("id").equals(brand.getId())){
							//disabled: $option.prop('disabled'),
					        //selected: $option.prop('selected'),
							map.put("selected", "selected");
							break;
						}
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
	
	@RequestMapping(value = "/saveprovider.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveProvider(HttpServletRequest request, @RequestBody String data) {
		logger.info("this is [saveProvider.do] start ...");
		Integer providerId=0;
		String providerName=null, contactName=null, contactPhoneNumber=null, brandIds=null;
		Map<String, Object> result=new HashMap<String, Object>();
		
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [saveProvider.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [saveProvider.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		try{
			JSONObject json=JSONObject.fromString(data);
			providerId=Integer.parseInt(
					json.getString("providerId")==null||json.getString("providerId").equals("")||json.getString("providerId").equals("null")
						?"0":json.getString("providerId"));
			providerName=json.getString("providerName")==null||json.getString("providerName").equals("")||json.getString("providerName").equals("null")
					?"":json.getString("providerName");
			contactName=json.getString("contactName")==null||json.getString("contactName").equals("")||json.getString("contactName").equals("null")
					?"":json.getString("contactName");
			contactPhoneNumber=json.getString("contactPhoneNumber")==null||json.getString("contactPhoneNumber").equals("")||json.getString("contactPhoneNumber").equals("null")
					?"":json.getString("contactPhoneNumber");
			brandIds=json.getString("brandIds")==null||json.getString("brandIds").equals("")||json.getString("brandIds").equals("null")
					?"":json.getString("brandIds");
		}catch(Exception ex){
			logger.info("this is [saveProvider.do] get parameter error ...");
			result.put("status", -1);
			result.put("data", "parameters error");
			ex.printStackTrace();
		}
		
		if (result.isEmpty()){
			logger.info("this is [saveProvider.do] providerId ["+providerId+"] ...");
			logger.info("this is [saveProvider.do] providerName ["+providerName+"] ...");
			logger.info("this is [saveProvider.do] contactName ["+contactName+"] ...");
			logger.info("this is [saveProvider.do] contactPhoneNumber ["+contactPhoneNumber+"] ...");
			logger.info("this is [saveProvider.do] brandIds ["+brandIds+"] ...");
			
			Providers provider=null;
			if (providerId!=0){
				logger.info("this is [saveProvider.do] find provider by id ["+providerId+"] ...");
				provider=providerManagementService.get(providerId);
			}else{
				logger.info("this is [saveProvider.do] create new provider ...");
				provider=new Providers();
			}
			logger.info("this is [saveProvider.do] set attributes ...");
			provider.setProviderName(providerName);
			provider.setContactName(contactName);
			provider.setContactPhoneNumber(contactPhoneNumber);
			
			List<Brand> lstBrands=new ArrayList<Brand>();
			if (brandIds!=""){
				String[] arr=null;
				if (!brandIds.contains(",")){
					arr=new String[1];
					arr[0]=brandIds;
				}else{
					arr=brandIds.split(",");
				}
				
				for (int ind=0;ind<arr.length;ind++){
					lstBrands.add(brandManagementService.get(Integer.parseInt(arr[ind])));
				}
			}
			provider.setBrands(lstBrands);
			
			try{
				logger.info("this is [saveProvider.do] is saving ...");
				providerManagementService.save(provider);
				logger.info("this is [saveProvider.do] save provider done ...");
				result.put("status", 1);
				result.put("data", "save success!");
			}catch(Exception ex){
				logger.info("this is [saveProvider.do] save provider error ...");
				result.put("status", 1);
				result.put("data", "save failed, try again!");
				ex.printStackTrace();
			}
		}
		logger.info("this is [saveProvider.do] show result ["+result+"] ...");
		return result;
	}
	
	@RequestMapping(value = "/deleteprovider.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteProvider(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [deleteProvider.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [deleteProvider.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [deleteProvider.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		JSONObject json=JSONObject.fromString(data);
		String ids=json.getString("providerIds")==null||json.getString("providerIds").equals("")||json.getString("providerIds").equals("null")
				?"-1":json.getString("providerIds");
		logger.info("this is [deleteProvider.do] show providerIds ["+ids+"]");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		try{
			
			logger.info("this is [deleteProvider.do] is deleting ...");
			providerManagementService.deleteProviders(ids);
			parameters.put("status", 1);
			parameters.put("data", "operation success.");
		}catch(Exception ex){
			logger.info("this is [deleteProvider.do] delete error ...");
			ex.printStackTrace();
			parameters.put("status", 0);
			parameters.put("data", "operation failed, try again please.");
		}
		
		logger.info("this is [deleteProvider.do] end ...");
		return parameters;
	}
}
