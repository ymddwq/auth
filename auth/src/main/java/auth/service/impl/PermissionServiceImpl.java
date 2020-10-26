package auth.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import auth.form.PermissionForm;
import auth.mapper.PermissionMapper;
import auth.model.Permission;
import auth.service.PermissionService;
import base.exception.ExceptionInfo;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	private static Logger logger = Logger.getLogger(PermissionServiceImpl.class);

	@Autowired
	PermissionMapper permissionMapper;
	
	@Override
	public boolean insert(Permission record) throws Exception {
		logger.info("PermissionServiceImpl insert record: " + record);
		//校验是否重名
		Permission obj = permissionMapper.selectByName(record.getName());
		if(obj != null && !StringUtils.isEmpty(obj.getName())) {
			throw ExceptionInfo.THE_SAME_NAME;
		}
		if(1 <= permissionMapper.insert(record)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) throws Exception {
		logger.info("PermissionServiceImpl deleteByPrimaryKey id: " + id);
		permissionMapper.deleteByPrimaryKey(id);
		permissionMapper.deleteMenuPermissionByPrimaryKey(id);
		if(1 <= permissionMapper.deleteRolePermissionByPrimaryKey(id)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean updateByPrimaryKey(Permission record) throws Exception {
		logger.info("PermissionServiceImpl updateByPrimaryKey record: " + record);
		Permission oldObj = permissionMapper.selectByPrimaryKey(record.getId());
		if(!oldObj.getName().equals(record.getName())) {
			//校验是否重名
			Permission newObj = permissionMapper.selectByName(record.getName());
			if(newObj != null && !StringUtils.isEmpty(newObj.getName())) {
				throw ExceptionInfo.THE_SAME_NAME;
			}
		}
		if(1 == permissionMapper.updateByPrimaryKey(record)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PageInfo<Permission> selectAll(PermissionForm form) throws Exception {
		logger.info("PermissionServiceImpl selectAll form: " + form);
		PageHelper.startPage(form.getPageNum(), form.getPageSize());
		List<Permission> lists = permissionMapper.selectAll();
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(lists);
		return pageInfo;
	}
	
}
