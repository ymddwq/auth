package auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import auth.form.RoleForm;
import auth.model.Role;
import auth.service.RoleService;
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
		PageInfo<Role> list = null;
		try {
			list = roleService.selectAll(form);
			dataResult.setData(list);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController selectAll Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/selectRolesBySysUserId")
	@ResponseBody
	public DataResult selectRolesBySysUserId(Integer id) {
		DataResult dataResult = new DataResult();
		if(StringUtils.isEmpty(id)) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		List<Role> list = null;
		try {
			list = roleService.selectRolesBySysUserId(id);
			dataResult.setData(list);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController selectRolesBySysUserId Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {
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
		} catch (Exception e) {
			logger.error("RoleController insert Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		if(form != null && StringUtils.isEmpty(form.getId())) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		Role obj = new Role();
		try {
			BeanUtils.copyProperties(form, obj);
			roleService.updateByPrimaryKey(obj);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
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
