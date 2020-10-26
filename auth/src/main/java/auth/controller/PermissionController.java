package auth.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import auth.form.PermissionForm;
import auth.model.Permission;
import auth.service.PermissionService;
import base.exception.BaseException;
import base.exception.ExceptionInfo;
import base.result.DataResult;
import base.utils.AuthStringUtils;
import base.utils.ValidUtils;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	private static Logger logger = Logger.getLogger(PermissionController.class);
	
	@Autowired
	PermissionService permissionService;
	
	@RequestMapping("/select")
	@ResponseBody
	public DataResult selectAll(PermissionForm form) {
		DataResult dataResult = new DataResult();
		PageInfo<Permission> lists = null;
		try {
			lists = permissionService.selectAll(form);
			dataResult.setData(lists);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("PermissionController selectAll " + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(@Valid PermissionForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		
		Permission obj = new Permission();
		try {
			BeanUtils.copyProperties(form, obj);
			permissionService.insert(obj);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (BaseException e) {
			logger.error("PermissionController insert BaseException" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.THE_SAME_NAME);
		} catch (Exception e) {
			logger.error("PermissionController insert Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@Valid PermissionForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		if(StringUtils.isEmpty(form.getId())) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		
		Permission obj = new Permission();
		try {
			BeanUtils.copyProperties(form, obj);
			permissionService.updateByPrimaryKey(obj);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (BaseException e) {
			logger.error("PermissionController update BaseException" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.THE_SAME_NAME);
		} catch (Exception e) {
			logger.error("PermissionController update Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer id) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(StringUtils.isEmpty(id)) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		
		try {
			permissionService.deleteByPrimaryKey(id);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("PermissionController delete Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
}
