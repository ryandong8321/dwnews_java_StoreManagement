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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwnews.storemanagement.entity.Brand;
import com.dwnews.storemanagement.service.brand.IBrandManagementService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/brandmanagement")
public class BrandManagementController {
	
	protected static Logger logger = LoggerFactory.getLogger(BrandManagementController.class);

	@Autowired
	private IBrandManagementService brandManagementService;
	
	@RequestMapping(value = "/brandslist.do")
	public String brandsList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [brandsList.do] start ...");
		logger.info("this is [brandsList.do] end ...");
		return "brand/brandslist";
	}
	
	@RequestMapping(value = "/initbrandstable.do")
	@ResponseBody
	public String initBrandsTable(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [initBrandsTable.do] start ...");
		
		int rowNum = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int showCount = ServletRequestUtils.getIntParameter(request, "limit", 0);
		
		Map<String,Object> data=brandManagementService.findBrands(rowNum,showCount,null);
		
		String result=JSONObject.fromObject(data).toString();
		logger.info("this is [initBrandsTable.do] data ["+result+"] ...");
		logger.info("this is [initBrandsTable.do] end ...");
		return result;
	}
	
	@RequestMapping(value = "/savebrand.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveBrand(HttpServletRequest request, @RequestBody String data) {
		logger.info("this is [saveBrand.do] start ...");
		Integer brandId=0;
		String brandName=null;
		Map<String, Object> result=new HashMap<String, Object>();
		
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [saveBrand.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [saveBrand.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		try{
			JSONObject json=JSONObject.fromString(data);
			brandId=Integer.parseInt(
					json.getString("brandId")==null||json.getString("brandId").equals("")||json.getString("brandId").equals("null")
						?"0":json.getString("brandId"));
			brandName=json.getString("brandName")==null||json.getString("brandName").equals("")||json.getString("brandName").equals("null")
					?"":json.getString("brandName");
		}catch(Exception ex){
			logger.info("this is [saveBrand.do] get parameter error ...");
			result.put("status", -1);
			result.put("data", "parameters error");
			ex.printStackTrace();
		}
		
		if (result.isEmpty()){
			logger.info("this is [saveBrand.do] brandId ["+brandId+"] ...");
			logger.info("this is [saveBrand.do] brandName ["+brandName+"] ...");
			
			Brand brand=null;
			if (brandId!=0){
				logger.info("this is [saveBrand.do] find brand by id ["+brandId+"] ...");
				brand=brandManagementService.get(brandId);
			}else{
				logger.info("this is [saveBrand.do] create new brand ...");
				brand=new Brand();
			}
			logger.info("this is [saveBrand.do] set attributes ...");
			brand.setBrandName(brandName);
			
			try{
				logger.info("this is [saveBrand.do] is saving ...");
				brandManagementService.save(brand);
				logger.info("this is [saveBrand.do] save brand done ...");
				result.put("status", 1);
				result.put("data", "save success!");
			}catch(Exception ex){
				logger.info("this is [saveBrand.do] save brand error ...");
				result.put("status", 1);
				result.put("data", "save failed, try again!");
				ex.printStackTrace();
			}
		}
		logger.info("this is [saveBrand.do] show result ["+result+"] ...");
		return result;
	}
	
	@RequestMapping(value = "/deletebrand.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteBrand(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [deleteBrand.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [deleteBrand.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [deleteBrand.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		JSONObject json=JSONObject.fromString(data);
		String ids=json.getString("brandIds")==null||json.getString("brandIds").equals("")||json.getString("brandIds").equals("null")
				?"-1":json.getString("brandIds");
		logger.info("this is [deleteBrand.do] show brandIds ["+ids+"]");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		try{
			
			logger.info("this is [deleteBrand.do] is deleting ...");
			brandManagementService.deleteBrands(ids);
			parameters.put("status", 1);
			parameters.put("data", "operation success.");
		}catch(Exception ex){
			logger.info("this is [deleteBrand.do] delete error ...");
			ex.printStackTrace();
			parameters.put("status", 0);
			parameters.put("data", "operation failed, try again please.");
		}
		
		logger.info("this is [deleteBrand.do] end ...");
		return parameters;
	}

}
