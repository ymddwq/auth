package auth.service;

import com.github.pagehelper.PageInfo;

import auth.form.ModuleForm;
import auth.model.Module;

public interface ModuleService {

	boolean insert(Module record) throws Exception;
	boolean deleteByPrimaryKey(Integer id) throws Exception;
	boolean updateByPrimaryKey(Module record) throws Exception;
	PageInfo<Module> selectAll(ModuleForm form) throws Exception;
	
}
