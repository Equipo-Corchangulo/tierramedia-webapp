package persistence.commons;

import java.sql.SQLException;
import java.util.List;

import model.Itinerario;

public interface GenericDAO<T> {

	public Itinerario find(Integer id) throws SQLException;
	public List<T> findAll() throws SQLException;
	public int insert(T t) throws SQLException;
	public int update(T t) throws SQLException;
	public int delete(Integer id);
}
