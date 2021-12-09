package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipoAtraccion;
import model.TipoDeAtraccion;
import persistence.commons.GenericDAO;

public interface ITipoAtraccionDAO extends GenericDAO<TipoAtraccion>{

		
		public abstract TipoAtraccion findByname(String name) throws Exception;
		public abstract TipoAtraccion toTipoAtraccion(ResultSet res) throws SQLException;

}
