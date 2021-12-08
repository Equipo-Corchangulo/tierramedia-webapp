package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Atraccion;
import model.PerfilUsuario;
import persistence.commons.GenericDAO;

public interface IAtraccionDAO extends GenericDAO<Atraccion>{

	
	public abstract Atraccion findByname(String name) throws Exception;	
	public abstract Atraccion toAtraccion(ResultSet res) throws SQLException;

}
