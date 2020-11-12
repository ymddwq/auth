package auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import auth.form.MenuForm;
import auth.mapper.MenuMapper;
import auth.mapper.PermissionMapper;
import auth.model.Menu;
import auth.model.Permission;
import auth.service.MenuService;
import base.service.impl.BaseServiceImpl;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuForm, MenuMapper> implements MenuService {
	
	@Autowired
	PermissionMapper permissionMapper;
	
	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		logger.info(this.getClass().getName() + " deleteByPrimaryKey id: " + id);
		List<Integer> childrenIdList = new ArrayList<Integer>();
		//将要删除的菜单id放入集合
		childrenIdList.add(id);
		//将要删除的菜单的子菜单id放入集合
		queryChildrenMenuIdsById(id, childrenIdList);
		//删除菜单及子菜单
		int result = mapper.deleteByPrimaryKeys(childrenIdList);
		//删除菜单所对应的权限
		List<Permission> permissions = permissionMapper.selectByPrimaryKeys(childrenIdList);
		if(!CollectionUtils.isEmpty(permissions)) {
			List<Integer> permissionIds = new ArrayList<Integer>();
			for(Permission permission : permissions) {
				permissionIds.add(permission.getId());
			}
			permissionMapper.deleteByPrimaryKeys(permissionIds);
		}
		if(1 == result) {
			return true;
		} else {
			return false;
		}
	}
	
	//递归查询指定id的所有下级菜单id
	public void queryChildrenMenuIdsById(Integer id, List<Integer> childrenIdList) {
		List<Menu> list = mapper.selectByPid(id);
		if(list != null && list.size()>0) {
			for(Menu m : list) {
				childrenIdList.add(m.getId());
				queryChildrenMenuIdsById(m.getId(), childrenIdList);
			}
		}
	}

	//FIXME  查询数据库次数待优化
	@Override
	public List<Menu> selectAll() {
		//查询一级菜单
		List<Menu> list = mapper.selectByPid(null);
		//查询所有子菜单并装配
		List<Menu> resultList = new ArrayList<Menu>();
		if(list != null && list.size()>0) {
			queryChildrenMenusById(list, resultList);
			return resultList;
		}
		return resultList;
	}
	
	//递归查询指定id的所有下级菜单
	public void queryChildrenMenusById(List<Menu> list, List<Menu> resultList) {
		for(Menu m : list) {
			List<Menu> nextlist = mapper.selectByPid(m.getId());
			if(nextlist != null && nextlist.size()>0) {
				m.setChildrenMenus(nextlist);
				resultList.add(m);
				queryChildrenMenusById(nextlist, resultList);
			}
		}
	}
	
	//新增修改角色时使用
	@Override
	public List<Menu> selectAllMenuPermissions(Integer roleId) {
		//查询一级菜单
		List<Menu> list = mapper.selectByPid(null);
		List<Menu> resultList = new ArrayList<Menu>();
		if(list != null && list.size()>0) {
			HashMap<Integer, List<Permission>> permissionMap = queryPermissionsMapGroupByMenuId(roleId);
			queryAllMenuPermissions(list, resultList, permissionMap);
		}
		return resultList;
	}
	
	//递归查询指定id的所有下级菜单
	public void queryAllMenuPermissions(List<Menu> list, List<Menu> resultList, HashMap<Integer, List<Permission>> permissionMap) {
		for(Menu m : list) {
			List<Menu> nextlist = mapper.selectByPid(m.getId());
			if(nextlist != null && nextlist.size()>0) {
				m.setChildrenMenus(nextlist);
				resultList.add(m);
				queryAllMenuPermissions(nextlist, resultList, permissionMap);
			} else {
				m.setPermissions(permissionMap.get(m.getId()));
			}
		}
	}
	
	//将permission按照menuId分组，并将roleId对应的权限标记
	public HashMap<Integer, List<Permission>> queryPermissionsMapGroupByMenuId(Integer roleId) {
		//查询所有权限
		List<Permission> allPermissionList = permissionMapper.selectAll(null);
		HashMap<Integer, List<Permission>> permissionMap = new HashMap<>();
		if(!CollectionUtils.isEmpty(allPermissionList)) {
			//如果roleId不为空，则表示要返显
			List<Integer> queryByRoleIdList = null;
			if(roleId != null) {
				//查询出此角色包含的权限
				queryByRoleIdList = permissionMapper.selectPermissionsByRoleId(roleId);
			}
			for(Permission permission : allPermissionList) {
				if(permission != null) {
					//将权限标记，修改角色时用于反显此角色拥有的权限
					if(roleId != null && queryByRoleIdList != null && queryByRoleIdList.contains(permission.getId())) {
						permission.setFlag("1");
					}
					//按照menuId分组
					if(permissionMap.containsKey(permission.getMenuId())) {//有则加入list
						permissionMap.get(permission.getMenuId()).add(permission);
					} else {//没有则创建list
						List<Permission> pList = new ArrayList<>();
						pList.add(permission);
						permissionMap.put(permission.getMenuId(), pList);
					}
				}
			}
		}
		return permissionMap;
	}
	
}
