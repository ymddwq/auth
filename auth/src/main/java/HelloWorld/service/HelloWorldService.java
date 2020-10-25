package HelloWorld.service;

import java.util.List;

import HelloWorld.model.RoleModel;

public interface HelloWorldService {

	RoleModel selectById(Long id);
	
	int insert(RoleModel roleModel);
	
	List<RoleModel> selectAll();
	
}
