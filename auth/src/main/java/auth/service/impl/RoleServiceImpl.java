package auth.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import auth.form.RoleForm;
import auth.mapper.RoleMapper;
import auth.model.Role;
import auth.service.RoleService;
import base.exception.ExceptionInfo;
import base.utils.AuthRedisLockUtils;

@Service
public class RoleServiceImpl implements RoleService {
	
	private static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public boolean insert(Role record) throws Exception {
		logger.info("RoleServiceImpl insert record: " + record);
		int result = 0;
		String uuid = UUID.randomUUID().toString();
		try {
			if(AuthRedisLockUtils.redisLock(redisTemplate, "RoleServiceImpl_insert", uuid, 3)) {
				//校验是否重名
				List<Role> lists = roleMapper.selectByName(record.getName());
				if(lists != null && lists.size()>0) {
					throw ExceptionInfo.THE_SAME_NAME;
				}
				int insert = roleMapper.insert(record);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			AuthRedisLockUtils.redisUnlock(redisTemplate, "RoleServiceImpl_insert", uuid);
		}
		if(1 <= result) {
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
			List<Role> lists = roleMapper.selectByName(record.getName());
			if(lists != null && lists.size()>0) {
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
