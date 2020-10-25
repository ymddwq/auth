package HelloWorld.mapper;

import java.util.List;

import HelloWorld.model.RoleModel;

public interface HelloWorldMapper{

	RoleModel selectById(Long id);
	
	int insert(RoleModel roleModel);
	
	List<RoleModel> selectAll();
	
}
