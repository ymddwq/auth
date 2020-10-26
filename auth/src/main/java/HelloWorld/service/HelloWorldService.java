package HelloWorld.service;

import com.github.pagehelper.PageInfo;

import HelloWorld.form.RoleFrom;
import HelloWorld.model.RoleModel;

public interface HelloWorldService {

	RoleModel selectById(Integer id) throws Exception;
	
	int insert(RoleModel roleModel) throws Exception;
	
	PageInfo<RoleModel> selectAll(RoleFrom roleFrom) throws Exception;

}
