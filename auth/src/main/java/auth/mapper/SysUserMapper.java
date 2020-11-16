package auth.mapper;

import java.util.List;

import auth.form.SysUserForm;
import auth.model.SysUser;
import auth.model.SysUserPermission;
import auth.model.SysUserRole;
import base.mapper.BaseMapper;

public interface SysUserMapper extends BaseMapper<SysUser, SysUserForm> {
   
	int insertSysUserRoles(List<SysUserRole> list);
	int deleteSysUserRoleBySysUserId(Integer id);
	SysUser selectSysUserByNameAndPassword(SysUser record);
	List<SysUserPermission> selectPermissionsBySysUserId(Integer id);
}