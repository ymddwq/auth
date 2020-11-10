package auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import auth.form.RoleForm;
import auth.mapper.RoleMapper;
import auth.model.Permission;
import auth.model.Role;
import auth.model.RolePermission;
import auth.service.RoleService;
import base.service.impl.BaseServiceImpl;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleForm, RoleMapper> implements RoleService {
	
	@Override
	public boolean insert(Role record) {
		logger.info(this.getClass().getName() + " insert record: " + record);
		//新增角色
		int result  = mapper.insert(record);
		if(1 == result) {
			//新增角色所对应的权限
			List<Permission> permissions = record.getPermissions();
			if(!CollectionUtils.isEmpty(permissions)) {
				List<RolePermission> rps = new ArrayList<RolePermission>();
				for(Permission permission : permissions) {
					RolePermission rp = new RolePermission();
					rp.setPermissionId(permission.getId());
					rp.setRoleId(record.getId());
					rps.add(rp);
				}
				mapper.insertRolePermissions(rps);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		logger.info(this.getClass().getName() + " deleteByPrimaryKey id: " + id);
		if(1 == mapper.deleteByPrimaryKey(id)) {
			//删除角色权限表中此角色id对应的数据
			mapper.deleteRolePermissionByRoleId(id);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean updateByPrimaryKey(Role record) {
		logger.info(this.getClass().getName() + " updateByPrimaryKey record: " + record);
		int result = mapper.updateByPrimaryKey(record);
		if(1 == result) {
			//删除原角色对应的权限
			mapper.deleteRolePermissionByRoleId(record.getId());
			//新增角色所对应的权限
			List<Permission> permissions = record.getPermissions();
			if(!CollectionUtils.isEmpty(permissions)) {
				List<RolePermission> rps = new ArrayList<RolePermission>();
				for(Permission permission : permissions) {
					RolePermission rp = new RolePermission();
					rp.setPermissionId(permission.getId());
					rp.setRoleId(record.getId());
					rps.add(rp);
				}
				mapper.insertRolePermissions(rps);
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<Permission> selectPermissionsByRoleId(Integer id) {
		return mapper.selectPermissionsByRoleId(id);
	}

}
