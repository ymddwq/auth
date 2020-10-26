package auth.service;

import java.util.List;

import auth.model.Menu;

public interface MenuService {

	boolean insert(Menu record) throws Exception;
	boolean deleteByPrimaryKey(Integer id) throws Exception;
	boolean updateByPrimaryKey(Menu record) throws Exception;
	List<Menu> selectAll() throws Exception;
	
}
