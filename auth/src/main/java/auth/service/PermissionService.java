package auth.service;

import com.github.pagehelper.PageInfo;

import auth.form.PermissionForm;
import auth.model.Permission;

public interface PermissionService {

	boolean insert(Permission record) throws Exception;
	boolean deleteByPrimaryKey(Integer id) throws Exception;
	boolean updateByPrimaryKey(Permission record) throws Exception;
	PageInfo<Permission> selectAll(PermissionForm form) throws Exception;
	
}
