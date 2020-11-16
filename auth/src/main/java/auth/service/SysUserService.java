package auth.service;

import javax.servlet.http.HttpServletRequest;

import auth.form.SysUserForm;
import auth.model.SysUser;
import base.service.BaseService;

public interface SysUserService extends BaseService<SysUser, SysUserForm> {
	
	public SysUser sysUserLogin(HttpServletRequest request, SysUser record) throws Exception;
	public boolean sysUserLogout(HttpServletRequest request);
	
}
