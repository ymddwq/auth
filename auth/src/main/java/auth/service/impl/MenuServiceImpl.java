package auth.service.impl;

import java.util.ArrayList;
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
		List<Menu> lists = mapper.selectByPid(id);
		if(lists != null && lists.size()>0) {
			for(Menu m : lists) {
				childrenIdList.add(m.getId());
				queryChildrenMenuIdsById(m.getId(), childrenIdList);
			}
		}
	}

	@Override
	public List<Menu> selectAll() {
		//查询一级菜单
		List<Menu> lists = mapper.selectByPid(null);
		List<Menu> resultList = new ArrayList<Menu>();
		//查询所有子菜单并装配
		queryChildrenMenusById(lists, resultList);
		return lists;
	}
	
	//递归查询指定id的所有下级菜单
	public void queryChildrenMenusById(List<Menu> lists, List<Menu> resultList) {
		if(lists != null && lists.size()>0) {
			for(Menu m : lists) {
				List<Menu> nextlists = mapper.selectByPid(m.getId());
				if(nextlists != null && nextlists.size()>0) {
					m.setChildrenMenus(nextlists);
					resultList.add(m);
					queryChildrenMenusById(nextlists, resultList);
				}
			}
		}
	}
	
}
