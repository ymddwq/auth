package HelloWorld.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import HelloWorld.form.RoleFrom;
import HelloWorld.mapper.HelloWorldMapper;
import HelloWorld.model.RoleModel;
import HelloWorld.service.HelloWorldService;
import base.exception.ExceptionInfo;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	
	private static Logger logger = Logger.getLogger(HelloWorldServiceImpl.class);

	@Autowired
	HelloWorldMapper helloWorldMapper;
	
	@Override
	public RoleModel selectById(Integer id) {
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
	public int insert(RoleModel roleModel) throws Exception {
		int result = 0;
		try {
			//测试事务
//			System.out.println(1/0);
			result = helloWorldMapper.insert(roleModel);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//throw ExceptionInfo.FAIL;
		}
		return result;
	}
	
}
