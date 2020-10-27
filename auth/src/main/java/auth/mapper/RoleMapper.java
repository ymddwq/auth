package auth.mapper;

import java.util.List;

import auth.model.Role;

public interface RoleMapper {

	int insert(Role record) throws Exception;
	int updateByPrimaryKey(Role record) throws Exception;
	int deleteByPrimaryKey(Integer id) throws Exception;
	Role selectByPrimaryKey(Integer id) throws Exception;
	List<Role> selectAll() throws Exception;
	Role selectByName(String name) throws Exception;

}