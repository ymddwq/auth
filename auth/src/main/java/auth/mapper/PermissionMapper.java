package auth.mapper;

import java.util.List;

import auth.form.PermissionForm;
import auth.model.Permission;
import base.mapper.BaseMapper;

public interface PermissionMapper extends BaseMapper<Permission, PermissionForm> {

	int deleteRolePermissionByPermissionId(Integer id);
	List<Integer> selectPermissionsByRoleId(Integer roldId);
	
}