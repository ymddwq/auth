package auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import auth.mapper.MenuMapper;
import auth.model.Menu;
import auth.service.MenuService;
import base.exception.ExceptionInfo;

@Service
public class MenuServiceImpl implements MenuService {
	
	private static Logger logger = Logger.getLogger(MenuServiceImpl.class);

	@Autowired
	MenuMapper menuMapper;
	
	@Override
	public boolean insert(Menu record) throws Exception {
		logger.info("MenuServiceImpl insert record: " + record);
		//校验是否重名
		Menu menu = menuMapper.selectByName(record.getName());
		if(menu != null && !StringUtils.isEmpty(menu.getName())) {
			throw ExceptionInfo.THE_SAME_NAME;
		}
		if(1 == menuMapper.insert(record)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) throws Exception {
		logger.info("MenuServiceImpl deleteByPrimaryKey id: " + id);
		List<Integer> childrenIdList = new ArrayList<Integer>();
		//将要删除的菜单id放入集合
		childrenIdList.add(id);
		//将要删除的菜单的子菜单id放入集合
		queryChildrenMenuIdsById(id, childrenIdList);
		if(1 <= menuMapper.deleteByPrimaryKey(childrenIdList)) {
			return true;
		} else {
			return false;
		}
	}
	
	//递归查询指定id的所有下级菜单id
	public void queryChildrenMenuIdsById(Integer id, List<Integer> childrenIdList) throws Exception {
		List<Menu> lists = menuMapper.selectByPid(id);
		if(lists != null && lists.size()>0) {
			for(Menu m : lists) {
				childrenIdList.add(m.getId());
				queryChildrenMenuIdsById(m.getId(), childrenIdList);
			}
		}
	}

	@Override
	public boolean updateByPrimaryKey(Menu record) throws Exception {
		logger.info("MenuServiceImpl updateByPrimaryKey record: " + record);
		Menu oldObj = menuMapper.selectByPrimaryKey(record.getId());
		if(!oldObj.getName().equals(record.getName())) {
			//校验是否重名
			Menu newObj = menuMapper.selectByName(record.getName());
			if(newObj != null && !StringUtils.isEmpty(newObj.getName())) {
				throw ExceptionInfo.THE_SAME_NAME;
			}
		}
		if(1 == menuMapper.updateByPrimaryKey(record)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Menu> selectAll() throws Exception {
		//查询一级菜单
		List<Menu> lists = menuMapper.selectByPid(null);
		List<Menu> resultList = new ArrayList<Menu>();
		//查询所有子菜单并装配
		queryChildrenMenusById(lists, resultList);
		return lists;
	}
	
	//递归查询指定id的所有下级菜单
	public void queryChildrenMenusById(List<Menu> lists, List<Menu> resultList) throws Exception {
		if(lists != null && lists.size()>0) {
			for(Menu m : lists) {
				List<Menu> nextlists = menuMapper.selectByPid(m.getId());
				if(nextlists != null && nextlists.size()>0) {
					m.setChildrenMenus(nextlists);
					resultList.add(m);
					queryChildrenMenusById(nextlists, resultList);
				}
			}
		}
	}
	
}
