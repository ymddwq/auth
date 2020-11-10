package auth.service;

import java.util.List;

import auth.form.RoleForm;
import auth.model.Permission;
import auth.model.Role;
import base.service.BaseService;

public interface RoleService extends BaseService<Role, RoleForm> {
	
	List<Permission> selectPermissionsByRoleId(Integer id);

}
