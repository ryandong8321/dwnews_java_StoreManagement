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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dwnews.storemanagement.entity.Departments;
import com.dwnews.storemanagement.service.department.IDepartmentManagementService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/departmentmanagement")
public class DepartmentManagementController {
	
	protected static Logger logger = LoggerFactory.getLogger(DepartmentManagementController.class);

	@Autowired
	private IDepartmentManagementService departmentManagementService;
	
	@RequestMapping(value = "/departmentstree.do")
	public String departmentsTree(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [departmentsTree.do] start ...");
		logger.info("this is [departmentsTree.do] end ...");
		return "department/departmentstree";
	}
	
	@RequestMapping(value = "/inittreedata.do")
	@ResponseBody
	public String initTreeData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		logger.info("this is [initTreeData.do] start ...");
		List<Map<String, Object>> data=departmentManagementService.findDepartmentsForTree(null);
		String strJsonResult = JSONArray.fromObject(data).toString();
		logger.info("this is [initTreeData.do] json [" + strJsonResult + "]");
		logger.info("this is [initTreeData.do] end ...");
		return strJsonResult;
	}
	
	@RequestMapping(value = "/savedepartment.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveDepartment(HttpServletRequest request, @RequestBody String data) {
		logger.info("this is [saveDepartment.do] start ...");
		Integer departmentId=0,parentId=0;
		String departmentName=null;
		Map<String, Object> result=new HashMap<String, Object>();
		
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [saveDepartment.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [saveDepartment.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		try{
			JSONObject json=JSONObject.fromString(data);
			parentId=Integer.parseInt(
					json.getString("parentId")==null||json.getString("parentId").equals("")||json.getString("parentId").equals("null")
						?"-1":json.getString("parentId"));
			departmentId=Integer.parseInt(
					json.getString("departmentId")==null||json.getString("departmentId").equals("")||json.getString("departmentId").equals("null")
						?"0":json.getString("departmentId"));
			departmentName=json.getString("departmentName")==null||json.getString("departmentName").equals("")||json.getString("departmentName").equals("null")
					?"":json.getString("departmentName");
		}catch(Exception ex){
			logger.info("this is [savedepartment.do] get parameter error ...");
			result.put("status", -1);
			result.put("data", "parameters error");
			ex.printStackTrace();
		}
		
		if (result.isEmpty()){
			logger.info("this is [saveDepartment.do] departmentId ["+departmentId+"] ...");
			logger.info("this is [saveDepartment.do] parentId ["+parentId+"] ...");
			logger.info("this is [saveDepartment.do] departmentName ["+departmentName+"] ...");
			
			Departments department=null;
			if (departmentId!=0){
				logger.info("this is [saveDepartment.do] find department by id ["+departmentId+"] ...");
				department=departmentManagementService.get(departmentId);
			}else{
				logger.info("this is [saveDepartment.do] create new department ...");
				department=new Departments();
			}
			logger.info("this is [saveDepartment.do] set attributes ...");
			department.setDepartmentName(departmentName);
			department.setParentId(parentId);
			
			try{
				logger.info("this is [saveDepartment.do] is saving ...");
				departmentManagementService.save(department);
				logger.info("this is [saveDepartment.do] save department done ...");
				result.put("status", 1);
				result.put("data", "save success!");
			}catch(Exception ex){
				logger.info("this is [saveDepartment.do] save department error ...");
				result.put("status", 1);
				result.put("data", "save failed, try again!");
				ex.printStackTrace();
			}
		}
		
		logger.info("this is [saveDepartment.do] show result ["+result+"] ...");
		return result;
	}
	
	@RequestMapping(value = "/deletedepartment.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteDepartment(HttpServletRequest request,@RequestBody String data) {
		logger.info("this is [deleteDepartment.do] start ...");
		if (data!=null&&!data.equals("")){
			try {
				logger.info("this is [deleteDepartment.do] is decoding ...");
				data=URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.info("this is [deleteDepartment.do] occur error when program decoding ...");
				e.printStackTrace();
			}
		}
		
		JSONObject json=JSONObject.fromString(data);
		String ids=json.getString("departmentIds")==null||json.getString("departmentIds").equals("")||json.getString("departmentIds").equals("null")
				?"-1":json.getString("departmentIds");
		logger.info("this is [deleteDepartment.do] show departmentIds ["+ids+"]");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		try{
			
			logger.info("this is [deleteDepartment.do] is deleting ...");
			departmentManagementService.deleteDepartments(ids);
			parameters.put("status", 1);
			parameters.put("data", "operation success.");
		}catch(Exception ex){
			logger.info("this is [deleteDepartment.do] delete error ...");
			ex.printStackTrace();
			parameters.put("status", 0);
			parameters.put("data", "operation failed, try again please.");
		}
		
		logger.info("this is [deleteDepartment.do] end ...");
		return parameters;
	}
}
