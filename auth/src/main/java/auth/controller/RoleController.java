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

import auth.form.RoleForm;
import auth.model.Role;
import auth.service.RoleService;
import base.exception.BaseException;
import base.exception.ExceptionInfo;
import base.result.DataResult;
import base.utils.AuthStringUtils;
import base.utils.ValidUtils;

@Controller
@RequestMapping("/role")
public class RoleController {

	private static Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/select")
	@ResponseBody
	public DataResult selectAll(RoleForm form) {
		DataResult dataResult = new DataResult();
		PageInfo<Role> lists = null;
		try {
			lists = roleService.selectAll(form);
			dataResult.setData(lists);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController selectAll " + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(@Valid RoleForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		
		Role obj = new Role();
		try {
			BeanUtils.copyProperties(form, obj);
			roleService.insert(obj);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (BaseException e) {
			logger.error("RoleController insert BaseException" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.THE_SAME_NAME);
		} catch (Exception e) {
			logger.error("RoleController insert Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@Valid RoleForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		if(StringUtils.isEmpty(form.getId())) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		
		Role obj = new Role();
		try {
			BeanUtils.copyProperties(form, obj);
			roleService.updateByPrimaryKey(obj);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (BaseException e) {
			logger.error("RoleController update BaseException" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.THE_SAME_NAME);
		} catch (Exception e) {
			logger.error("RoleController update Exception" + AuthStringUtils.printStackTraceToString(e));
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
			roleService.deleteByPrimaryKey(id);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController delete Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
}
