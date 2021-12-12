package persistence.commons;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public T find(Integer id) throws SQLException;
	public List<T> findAll() throws SQLException;
	public int insert(T t);
	public int update(T t);
	public int delete(Integer id);
}