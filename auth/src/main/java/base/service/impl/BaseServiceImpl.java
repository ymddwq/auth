package base.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import base.form.PageForm;
import base.mapper.BaseMapper;
import base.service.BaseService;

@Service
public class BaseServiceImpl<T, F, M> implements BaseService<T, F> {
	
	public static Logger logger = Logger.getLogger(BaseServiceImpl.class);
	
	@Autowired
	public M mapper;

	@Override
	public PageInfo<T> selectAll(F form) {
		logger.info(this.getClass().getName() + " selectAll form: " + form);
		PageHelper.startPage(((PageForm) form).getPageNum(), ((PageForm) form).getPageSize());
		@SuppressWarnings("unchecked")
		List<T> list = ((BaseMapper<T, F>) mapper).selectAll(form);
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		return pageInfo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean insert(T t) throws Exception {
		logger.info(this.getClass().getName() + " insert po: " + t);
		if(1 == ((BaseMapper<T, F>) mapper).insert(t)) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		logger.info(this.getClass().getName() + " deleteByPrimaryKey id: " + id);
		if(1 == ((BaseMapper<T, F>) mapper).deleteByPrimaryKey(id)) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateByPrimaryKey(T t) {
		logger.info(this.getClass().getName() + " updateByPrimaryKey po: " + t);
		if(1 == ((BaseMapper<T, F>) mapper).updateByPrimaryKey(t)) {
			return true;
		} else {
			return false;
		}
	}

}
