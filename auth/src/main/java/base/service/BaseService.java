package base.service;

import com.github.pagehelper.PageInfo;

public interface BaseService<T, F> {

	boolean insert(T t) throws Exception;
	
	boolean deleteByPrimaryKey(Integer id);
	
	PageInfo<T> selectAll(F form);
	
	boolean updateByPrimaryKey(T t);
	
}
