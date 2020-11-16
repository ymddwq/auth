package auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import auth.form.SysUserLoginForm;
import auth.model.SysUser;
import auth.service.SysUserService;
import base.exception.ExceptionInfo;
import base.result.DataResult;
import base.utils.AuthStringUtils;
import base.utils.ValidUtils;

@Controller
@RequestMapping("/sysUserLogin")
public class SysUserLoginController {

	private static Logger logger = Logger.getLogger(SysUserLoginController.class);
	
	@Autowired
	SysUserService sysUserService;
	
	@RequestMapping("/sysUserLogin")
	@ResponseBody
	public DataResult sysUserLogin(HttpServletRequest request, @Valid SysUserLoginForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		try {
			SysUser obj = new SysUser();
			BeanUtils.copyProperties(form, obj);
			SysUser sysUserLogin = sysUserService.sysUserLogin(request, obj);
			dataResult.setData(sysUserLogin);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("SysUserLoginController sysUserLogin Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/sysUserLogout")
	@ResponseBody
	public DataResult sysUserLogout(HttpServletRequest request) {
		DataResult dataResult = new DataResult();
		try {
			sysUserService.sysUserLogout(request);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("SysUserLoginController sysUserLogin Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	//TODO 待实现 查询用户登录菜单以及权限，用户登录后点击菜单时使用
	@RequestMapping("/sysUserLoginMenus")
	@ResponseBody
	public DataResult sysUserLoginMenus() {
		
		
		return null;
	}
	
}
