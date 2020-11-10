package auth.model;

import base.model.BaseModel;

public class RolePermission extends BaseModel {

	private Integer roleId;
	
	private Integer permissionId;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

}
