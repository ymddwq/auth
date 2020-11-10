package base.mapper;

import java.util.List;

public interface BaseMapper<T, F> {
	
	int insert(T record);

	int deleteByPrimaryKey(Integer id);
	
	int deleteByPrimaryKeys(List<Integer> list);

	int updateByPrimaryKey(T record);

	T selectByPrimaryKey(Integer id);
	
	List<T> selectByPrimaryKeys(List<Integer> list);
	
	List<T> selectAll(F form);
	
	T selectByName(String name);

}
