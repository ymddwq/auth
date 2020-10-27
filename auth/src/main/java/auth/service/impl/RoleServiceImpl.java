package auth.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import auth.form.RoleForm;
import auth.mapper.RoleMapper;
import auth.model.Role;
import auth.service.RoleService;
import base.exception.ExceptionInfo;

@Service
public class RoleServiceImpl implements RoleService {
	
	private static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public boolean insert(Role record) throws Exception {
		logger.info("RoleServiceImpl insert record: " + record);
		//校验是否重名
		Role obj = roleMapper.selectByName(record.getName());
		if(obj != null && !StringUtils.isEmpty(obj.getName())) {
			throw ExceptionInfo.THE_SAME_NAME;
		}
		if(1 <= roleMapper.insert(record)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) throws Exception {
		logger.info("RoleServiceImpl deleteByPrimaryKey id: " + id);
		if(1 <= roleMapper.deleteByPrimaryKey(id)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean updateByPrimaryKey(Role record) throws Exception {
		logger.info("RoleServiceImpl updateByPrimaryKey record: " + record);
		Role oldObj = roleMapper.selectByPrimaryKey(record.getId());
		if(!oldObj.getName().equals(record.getName())) {
			//校验是否重名
			Role newObj = roleMapper.selectByName(record.getName());
			if(newObj != null && !StringUtils.isEmpty(newObj.getName())) {
				throw ExceptionInfo.THE_SAME_NAME;
			}
		}
		if(1 == roleMapper.updateByPrimaryKey(record)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PageInfo<Role> selectAll(RoleForm form) throws Exception {
		logger.info("RoleServiceImpl selectAll form: " + form);
		PageHelper.startPage(form.getPageNum(), form.getPageSize());
		List<Role> lists = roleMapper.selectAll();
		PageInfo<Role> pageInfo = new PageInfo<Role>(lists);
		return pageInfo;
	}
	
}
