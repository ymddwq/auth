package auth.controller;

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

import auth.form.SysUserForm;
import auth.model.SysUser;
import auth.service.SysUserService;
import base.exception.ExceptionInfo;
import base.result.DataResult;
import base.utils.AuthStringUtils;
import base.utils.ValidUtils;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {

	private static Logger logger = Logger.getLogger(SysUserController.class);
	
	@Autowired
	SysUserService sysUserService;
	
	@RequestMapping("/select")
	@ResponseBody
	public DataResult selectAll(SysUserForm form) {
		DataResult dataResult = new DataResult();
		PageInfo<SysUser> list = null;
		try {
			list = sysUserService.selectAll(form);
			dataResult.setData(list);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("SysUserController selectAll Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(@RequestBody @Valid SysUserForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		if(!form.getPassword().equals(form.getPassword1())) {
			dataResult.setCodeMsg(ExceptionInfo.USER_PASSWORD_NOT_SAME);
			return dataResult;
		}
		SysUser obj = new SysUser();
		try {
			BeanUtils.copyProperties(form, obj);
			sysUserService.insert(obj);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("SysUserController insert Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@RequestBody @Valid SysUserForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		if(form != null && StringUtils.isEmpty(form.getId())) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		if(StringUtils.isEmpty(form.getId())) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		SysUser obj = new SysUser();
		try {
			BeanUtils.copyProperties(form, obj);
			sysUserService.updateByPrimaryKey(obj);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("SysUserController update Exception" + AuthStringUtils.printStackTraceToString(e));
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
			sysUserService.deleteByPrimaryKey(id);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("SysUserController delete Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
}
