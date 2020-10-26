package auth.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import auth.form.ModuleForm;
import auth.mapper.ModuleMapper;
import auth.model.Module;
import auth.service.ModuleService;
import base.exception.ExceptionInfo;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	private static Logger logger = Logger.getLogger(ModuleServiceImpl.class);

	@Autowired
	ModuleMapper moduleMapper;
	
	@Override
	public boolean insert(Module record) throws Exception {
		logger.info("ModuleServiceImpl insert record: " + record);
		//校验是否重名
		Module module = moduleMapper.selectByName(record.getName());
		if(module != null && !StringUtils.isEmpty(module.getName())) {
			throw ExceptionInfo.THE_SAME_NAME;
		}
		if(1 == moduleMapper.insert(record)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) throws Exception {
		logger.info("ModuleServiceImpl deleteByPrimaryKey id: " + id);
		if(1 == moduleMapper.deleteByPrimaryKey(id)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateByPrimaryKey(Module record) throws Exception {
		logger.info("ModuleServiceImpl updateByPrimaryKey record: " + record);
		Module oldModule = moduleMapper.selectByPrimaryKey(record.getId());
		if(!oldModule.getName().equals(record.getName())) {
			//校验是否重名
			Module module = moduleMapper.selectByName(record.getName());
			if(module != null && !StringUtils.isEmpty(module.getName())) {
				throw ExceptionInfo.THE_SAME_NAME;
			}
		}
		if(1 == moduleMapper.updateByPrimaryKey(record)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PageInfo<Module> selectAll(ModuleForm form) throws Exception {
		logger.info("ModuleServiceImpl selectAll form: " + form);
		PageHelper.startPage(form.getPageNum(), form.getPageSize());
		List<Module> lists = moduleMapper.selectAll(form);
		PageInfo<Module> pageInfo = new PageInfo<Module>(lists);
		return pageInfo;
	}
	
}
