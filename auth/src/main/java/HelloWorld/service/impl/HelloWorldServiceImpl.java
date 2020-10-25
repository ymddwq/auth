package HelloWorld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import HelloWorld.form.RoleFrom;
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
	public PageInfo<RoleModel> selectAll(RoleFrom roleFrom) {
		PageHelper.startPage(roleFrom.getPageNum(), roleFrom.getPageSize());
		List<RoleModel> roles = helloWorldMapper.selectAll(roleFrom);
		PageInfo<RoleModel> pageInfo = new PageInfo<>(roles);
		return pageInfo;
	}

	@Override
	public int insert(RoleModel roleModel) {
		int result = helloWorldMapper.insert(roleModel);
		//测试事务
//		System.out.println(1/0);
		return result;
	}

}
