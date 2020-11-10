package auth.mapper;

import auth.form.MenuForm;
import auth.model.Menu;
import base.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MenuMapper extends BaseMapper<Menu, MenuForm> {

	List<Menu> selectByPid(@Param("id") Integer id);
	
}