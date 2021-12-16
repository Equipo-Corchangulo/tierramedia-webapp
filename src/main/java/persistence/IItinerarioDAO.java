package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Itinerario;
import persistence.commons.GenericDAO;

public interface IItinerarioDAO extends GenericDAO<Itinerario> {
	
	public abstract Itinerario findByUsername(String username) throws Exception;	
	public abstract Itinerario toPerfilUsuario(ResultSet res) throws SQLException;
	Itinerario toItinerario(ResultSet result) throws SQLException;
}
