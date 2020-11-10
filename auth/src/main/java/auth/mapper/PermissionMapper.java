package auth.mapper;

import auth.form.PermissionForm;
import auth.model.Permission;
import base.mapper.BaseMapper;

public interface PermissionMapper extends BaseMapper<Permission, PermissionForm> {

	int deleteRolePermissionByPermissionId(Integer id);
	
}