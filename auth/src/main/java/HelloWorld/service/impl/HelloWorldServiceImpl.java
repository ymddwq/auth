package HelloWorld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HelloWorld.mapper.HelloWorldMapper;
import HelloWorld.model.RoleModel;
import HelloWorld.service.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

	@Autowired
	HelloWorldMapper helloWorldMapper;
	
	@Override
	public RoleModel selectById(Long id) {
		return helloWorldMapper.selectById(id);
	}
	
	@Override
	public List<RoleModel> selectAll() {
		return helloWorldMapper.selectAll();
	}

	@Override
	public int insert(RoleModel roleModel) {
		int result = helloWorldMapper.insert(roleModel);
		//测试事务
//		System.out.println(1/0);
		return result;
	}

}
