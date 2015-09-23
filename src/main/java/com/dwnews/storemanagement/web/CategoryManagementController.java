package com.dwnews.storemanagement.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
			
			String parentIds=categoryManagementService.findCategoryParents(category.getId(), category.getParentId());
			logger.info("this is [showCategoryInfo.do] parentIds ["+parentIds+"] ...");
			modelMap.addAttribute("parentIds",parentIds);
		}
		modelMap.addAttribute("categoryId", categoryId);
		modelMap.addAttribute("operation_status", "-1");
		return "/category/categoriesinfo";
	}
	
	@RequestMapping(value = "/savecategory.do", method=RequestMethod.POST)
	public String saveCategory(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [saveCategory.do] start ...");
		int categoryId=0,parentId=0;
		String categoryName=null;
		try{
			categoryId=ServletRequestUtils.getIntParameter(request, "categoryId", 0);
			parentId=ServletRequestUtils.getIntParameter(request, "categoryParentId", -1);
			categoryName=ServletRequestUtils.getRequiredStringParameter(request, "categoryName");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		logger.info("this is [saveCategory.do] categoryId ["+categoryId+"] ...");
		logger.info("this is [saveCategory.do] parentId ["+parentId+"] ...");
		logger.info("this is [saveCategory.do] categoryName ["+categoryName+"] ...");
		
		Categories category=null;
		if (categoryId!=0){
			logger.info("this is [saveCategory.do] find category by id ["+categoryId+"] ...");
			category=categoryManagementService.get(categoryId);
		}else{
			logger.info("this is [saveCategory.do] create new category ...");
			category=new Categories();
		}
		logger.info("this is [saveCategory.do] set attributes ...");
		category.setCategoryName(categoryName);
		category.setParentId(parentId);
		
		try{
			logger.info("this is [saveCategory.do] is saving ...");
			categoryManagementService.save(category);
			logger.info("this is [saveCategory.do] save category done ...");
			modelMap.addAttribute("category", category);
			modelMap.addAttribute("operation_status", "1");
		}catch(Exception ex){
			logger.info("this is [saveCategory.do] save category error ...");
			ex.printStackTrace();
			modelMap.addAttribute("operation_status", "0");
		}
		
		logger.info("this is [saveCategory.do] find parents ...");
		String parentIds=categoryManagementService.findCategoryParents(category.getId(), category.getParentId());
		logger.info("this is [saveCategory.do] parentIds ["+parentIds+"] ...");
		
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
		Integer notExistId=Integer.parseInt(
				json.getString("notExistId")==null||json.getString("notExistId").equals("")||json.getString("notExistId").equals("null")
					?"0":json.getString("notExistId"));
		
		logger.info("this is [getCategoryRank.do] show parentId ["+parentId+"]");
		logger.info("this is [getCategoryRank.do] show ids ["+ids+"]");
		logger.info("this is [getCategoryRank.do] show notExistId ["+notExistId+"]");
		
		Map<String, Object> result=new HashMap<String, Object>();
		if (parentId==0){
			result.put("status", 0);
			result.put("data", "wrong parameters");
		}else{
			List<Map<String, Object>> lst=categoryManagementService.findCategoryRank(parentId,ids,notExistId);
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
	
	@RequestMapping(value = "/deletecategory.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deletecategory(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [deletecategory.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [deletecategory.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [deletecategory.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		JSONObject json=JSONObject.fromString(data);
		Integer id=Integer.parseInt(
			json.getString("id")==null||json.getString("id").equals("")||json.getString("id").equals("null")
				?"-1":json.getString("id"));
		logger.info("this is [deletecategory.do] show id ["+id+"]");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		try{
			logger.info("this is [deletecategory.do] is deleting ...");
			categoryManagementService.delete(id);
			parameters.put("status", 1);
		}catch(Exception ex){
			logger.info("this is [deletecategory.do] delete error ...");
			ex.printStackTrace();
			parameters.put("status", 0);
		}
		
		logger.info("this is [deletecategory.do] end ...");
		return parameters;
	}
	

}
