package auth.service.impl;

import org.springframework.stereotype.Service;

import auth.form.PermissionForm;
import auth.mapper.PermissionMapper;
import auth.model.Permission;
import auth.service.PermissionService;
import base.service.impl.BaseServiceImpl;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, PermissionForm, PermissionMapper> implements PermissionService {
	
	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		logger.info(this.getClass().getName() + " deleteByPrimaryKey id: " + id);
		int result = mapper.deleteByPrimaryKey(id);
		int result1 = mapper.deleteRolePermissionByPermissionId(id);
		if(1 == result && 1 <= result1) {
			return true;
		} else {
			return false;
		}
	}
	
}
