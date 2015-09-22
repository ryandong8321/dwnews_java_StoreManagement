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

import com.dwnews.storemanagement.entity.Categories;
import com.dwnews.storemanagement.service.category.ICategoryManagementService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/categorymanagement")
public class CategoryManagementController {

	protected static Logger logger = LoggerFactory.getLogger(CategoryManagementController.class);

	@Autowired
	private ICategoryManagementService categoryManagementService;

	@RequestMapping(value = "/categorieslist.do")
	public String categoriesList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [categoriesList.do] start ...");
		logger.info("this is [categoriesList.do] end ...");
		return "category/categorieslist";
	}

	@RequestMapping(value = "/inittable.do")
	@ResponseBody
	public String initCategoriesList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [initCategoriesList.do] start ...");

		int rowNum = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int showCount = ServletRequestUtils.getIntParameter(request, "limit", 0);
		
		Map<String, Object> result = categoryManagementService.findCategories(rowNum,showCount,null);
		String strJsonResult = JSONObject.fromObject(result).toString();
		logger.info("this is [initCategoriesList.do] json [" + strJsonResult + "]");
		logger.info("this is [initCategoriesList.do] end ...");
		return strJsonResult;
	}
	
	@RequestMapping(value = "/showcategory.do")
	public String showCategoryInfo(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [showCategoryInfo.do] start ...");
		int categoryId=0;
		try{
			categoryId=ServletRequestUtils.getIntParameter(request, "categoryId", 0);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		logger.info("this is [showCategoryInfo.do] categoryId ["+categoryId+"] ...");
		if (categoryId!=0){
			Categories category=categoryManagementService.get(categoryId);
			logger.info("this is [showCategoryInfo.do] category ["+category+"] ...");
			modelMap.addAttribute("category", category);
		}
		modelMap.addAttribute("categoryId", categoryId);
		return "/category/categoriesinfo";
	}
	
	@RequestMapping(value = "/getcategoryrank.do",method=RequestMethod.POST)
	@ResponseBody
	public String getCategoryRank(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [getCategoryRank.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [getCategoryRank.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [getCategoryRank.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		JSONObject json=JSONObject.fromString(data);
		Integer parentId=Integer.parseInt(
				json.getString("parentId")==null||json.getString("parentId").equals("")||json.getString("parentId").equals("null")
					?"0":json.getString("parentId"));
		String ids=json.getString("ids")==null||json.getString("ids").equals("")||json.getString("ids").equals("null")
					?"-1":json.getString("ids");
		
		logger.info("this is [getCategoryRank.do] show parentId ["+parentId+"]");
		logger.info("this is [getCategoryRank.do] show ids ["+ids+"]");
		
		Map<String, Object> result=new HashMap<String, Object>();
		if (parentId==0){
			result.put("status", 0);
			result.put("data", "wrong parameters");
		}else{
			List<Map<String, Object>> lst=categoryManagementService.findCategoryRank(parentId,ids);
			result.put("status", 1);
			result.put("data", lst);
		}
		String resultJson=JSONObject.fromObject(result).toString();
		logger.info("this is [getCategoryRank.do] show json ["+resultJson+"]");
		return resultJson;
	}
	
	@RequestMapping(value = "/getmodifiedinfo.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getModifiedInfo(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [getModifiedInfo.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [getModifiedInfo.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [getModifiedInfo.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		JSONObject json=JSONObject.fromString(data);
		Integer id=Integer.parseInt(
			json.getString("id")==null||json.getString("id").equals("")||json.getString("id").equals("null")
				?"-1":json.getString("id"));
		logger.info("this is [getModifiedInfo.do] show id ["+id+"]");
		
		logger.info("this is [getModifiedInfo.do] is doing finding action ...");
		
		Categories category=categoryManagementService.get(id);
		Map<String, Object> parameters = new HashMap<String, Object>();
		if (category!=null){
			parameters.put("categoryName", category.getCategoryName());
		}
		
		logger.info("this is [getModifiedInfo.do] end ...");
		return parameters;
	}
	
	@RequestMapping(value = "/savecategoryinfo.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveCategoryInfo(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [saveCategoryInfo.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [saveCategoryInfo.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [saveCategoryInfo.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		JSONObject json=JSONObject.fromString(data);
		Integer id=Integer.parseInt(
			json.getString("id")==null||json.getString("id").equals("")||json.getString("id").equals("null")
				?"-1":json.getString("id"));
		String categoryName=json.getString("categoryName");
		logger.info("this is [saveCategoryInfo.do] show id ["+id+"] category-name ["+categoryName+"]");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		Categories category=new Categories();
		if (id!=-1){
			category.setId(id);
		}
		category.setCategoryName(categoryName);
		try{
			categoryManagementService.save(category);
			parameters.put("status", 1);
		}catch(Exception ex){
			ex.printStackTrace();
			parameters.put("status", 0);
		}
		
		logger.info("this is [saveCategoryInfo.do] end ...");
		return parameters;
	}

}
