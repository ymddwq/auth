package HelloWorld.service;

import com.github.pagehelper.PageInfo;

import HelloWorld.form.RoleFrom;
import HelloWorld.model.RoleModel;

public interface HelloWorldService {

	RoleModel selectById(Long id);
	
	int insert(RoleModel roleModel);
	
	PageInfo<RoleModel> selectAll(RoleFrom roleFrom);
	
}
