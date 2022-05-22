package dao;

import java.util.List;

import composant.User;

public interface Dao<T> {
	
	T get(long id);
	
	//List<T> getSelected(long id);
	
	List<T> getAll();
	
	void save(T t);
	
	void update(T t);
	
	void delete(T t);

	boolean exist(T t);
}
