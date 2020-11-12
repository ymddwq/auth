package auth.model;

import java.util.List;

import base.model.BaseModel;

public class Menu extends BaseModel {

	private List<Menu> childrenMenus;
	
	private Integer enabled;
	
	private String addPermissionFlag;//1可以添加权限2不能添加权限
	
	private String name;
	
	private Integer pid;
	
	private List<Permission> permissions;

	public List<Menu> getChildrenMenus() {
		return childrenMenus;
	}

	public void setChildrenMenus(List<Menu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getAddPermissionFlag() {
		return addPermissionFlag;
	}

	public void setAddPermissionFlag(String addPermissionFlag) {
		this.addPermissionFlag = addPermissionFlag;
	}

}