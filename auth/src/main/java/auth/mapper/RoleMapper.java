package auth.mapper;

import java.util.List;

import auth.form.RoleForm;
import auth.model.Permission;
import auth.model.Role;
import auth.model.RolePermission;
import base.mapper.BaseMapper;

public interface RoleMapper extends BaseMapper<Role, RoleForm> {

	int insertRolePermissions(List<RolePermission> list);
	int deleteRolePermissionByRoleId(Integer id);
	List<Permission> selectPermissionsByRoleId(Integer id);
	
}