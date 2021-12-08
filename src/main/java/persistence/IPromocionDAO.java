package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Promocion;
import persistence.commons.GenericDAO;

public interface IPromocionDAO extends GenericDAO<Promocion> {
	public Promocion toPromocion(ResultSet result)throws SQLException;
}
