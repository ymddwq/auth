package auth.service;

import com.github.pagehelper.PageInfo;

import auth.form.RoleForm;
import auth.model.Role;

public interface RoleService {

	boolean insert(Role record) throws Exception;
	boolean updateByPrimaryKey(Role record) throws Exception;
	boolean deleteByPrimaryKey(Integer id) throws Exception;
	PageInfo<Role> selectAll(RoleForm form) throws Exception;
	
}
