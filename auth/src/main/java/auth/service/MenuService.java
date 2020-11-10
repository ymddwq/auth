package auth.service;

import java.util.List;

import auth.form.MenuForm;
import auth.model.Menu;
import base.service.BaseService;

public interface MenuService extends BaseService<Menu, MenuForm> {

	List<Menu> selectAll();
	
}
