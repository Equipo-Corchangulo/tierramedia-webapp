package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipoDeAtraccion;
import persistence.commons.GenericDAO;

public interface TipoAtraccionDAO extends GenericDAO<TipoDeAtraccion>{

		
		public abstract int findByname(String name) throws Exception;	
		public abstract TipoDeAtraccion toAtraccion(ResultSet res) throws SQLException;

}
