package auth.mapper;

import java.util.List;

import auth.model.Permission;

public interface PermissionMapper {

	int insert(Permission record) throws Exception;
	int deleteByPrimaryKey(Integer id) throws Exception;
	int deleteRolePermissionByPrimaryKey(Integer id) throws Exception;
	int deleteMenuPermissionByPrimaryKey(Integer id) throws Exception;
	
	int updateByPrimaryKey(Permission record) throws Exception;
	Permission selectByPrimaryKey(Integer id) throws Exception;
	List<Permission> selectAll() throws Exception;
	Permission selectByName(String name) throws Exception;
	
}